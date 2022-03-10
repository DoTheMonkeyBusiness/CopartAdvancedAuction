package com.cprt.advancedauction.firebaseauth.util

import com.cprt.advancedauction.auth.model.TokenInfoModel
import com.cprt.advancedauction.auth.model.UserCredentials
import com.cprt.advancedauction.auth.model.UserLoginState
import com.cprt.advancedauction.firebaseauth.data.requestBodyEntity.CredentialsRequestBody
import com.cprt.advancedauction.firebaseauth.data.requestBodyEntity.RefreshTokenRequestBody
import com.cprt.advancedauction.firebaseauth.data.responseEntity.RefreshTokenInfoEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.TokenInfoEntity
import com.cprt.advancedauction.firebaseauth.data.service.AuthService
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
        val tokenInfo = runCatching {
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

        return TokenInfoModel(
            accessToken = tokenInfo.accessToken,
            refreshToken = tokenInfo.refreshToken,
            expirationDate = tokenInfo.expirationDate,
        )
    }

    private suspend fun getUpdatedTokensByRefreshToken(
        refreshToken: String,
    ): TokenInfoModel {
        val tokenInfoEntity: RefreshTokenInfoEntity = refreshTokenService.load(
            RefreshTokenRequestBody(refreshToken = refreshToken)
        )

        return TokenInfoModel(
            accessToken = tokenInfoEntity.idToken ?: "",
            refreshToken = tokenInfoEntity.refreshToken ?: "",
            expirationDate = tokenInfoEntity.expiresIn?.toLong() ?: 0L,
        )
    }

    private suspend fun emailSignIn(credentials: UserCredentials): TokenInfoModel {
        val tokenInfoEntity: TokenInfoEntity = signInService.load(
            CredentialsRequestBody(
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
