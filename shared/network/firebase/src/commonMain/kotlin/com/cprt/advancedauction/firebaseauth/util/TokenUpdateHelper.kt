package com.cprt.advancedauction.firebaseauth.util

import com.cprt.advancedauction.auth.domain.model.TokenInfoModel
import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.auth.domain.model.UserLoginState
import com.cprt.advancedauction.firebaseauth.data.requestEntity.CredentialsRequestEntity
import com.cprt.advancedauction.firebaseauth.data.requestEntity.RefreshTokenRequestEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.RefreshTokenResponseEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.TokenInfoResponseEntity
import com.cprt.advancedauction.firebaseauth.data.service.auth.AuthService
import com.cprt.advancedauction.firebaseauth.exception.UpdateRefreshTokenException

internal class TokenUpdateHelper(
    private val signInService: AuthService.SignIn,
    private val refreshTokenService: AuthService.RefreshToken,
    private val authUtil: AuthUtil,
) {

    suspend fun updateToken(
        userLoginState: UserLoginState,
    ): TokenInfoModel {
        val token = getRefreshTokens(
            userLoginState = userLoginState,
        )

        authUtil.saveTokenIntoPreferences(token)

        return token
    }

    private suspend fun getRefreshTokens(
        userLoginState: UserLoginState,
    ): TokenInfoModel {
        val updateTokenException by lazy { UpdateRefreshTokenException("Can't update refresh token") }
        return runCatching {
            getUpdatedTokensByRefreshToken(
                refreshToken = userLoginState.tokenInfo.refreshToken,
            )
        }.getOrElse {
            when (userLoginState) {
                is UserLoginState.LoggedIn -> runCatching {
                    emailSignIn(userLoginState.userCredentials)
                }.getOrElse {
                    throw updateTokenException
                }
                else -> throw updateTokenException
            }
        }
    }

    private suspend fun getUpdatedTokensByRefreshToken(
        refreshToken: String,
    ): TokenInfoModel {
        val tokenInfoEntity: RefreshTokenResponseEntity = refreshTokenService.load(
            RefreshTokenRequestEntity(refreshToken = refreshToken)
        )

        return TokenInfoModel(
            accessToken = tokenInfoEntity.idToken ?: "",
            refreshToken = tokenInfoEntity.refreshToken ?: "",
            expirationDate = tokenInfoEntity.expiresIn?.toLong() ?: 0L,
        )
    }

    private suspend fun emailSignIn(credentials: UserCredentialsModel): TokenInfoModel {
        val tokenInfoEntity: TokenInfoResponseEntity = signInService.load(
            CredentialsRequestEntity(
                email = credentials.email,
                password = credentials.password
            )
        )

        return TokenInfoModel(
            accessToken = tokenInfoEntity.idToken ?: "",
            refreshToken = tokenInfoEntity.refreshToken ?: "",
            expirationDate = tokenInfoEntity.expiresIn?.toLong() ?: 0L,
        )
    }
}
