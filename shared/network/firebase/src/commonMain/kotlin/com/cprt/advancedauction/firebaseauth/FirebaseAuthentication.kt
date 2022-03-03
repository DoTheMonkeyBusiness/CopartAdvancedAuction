package com.cprt.advancedauction.firebaseauth

import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.auth.Authentication
import com.cprt.advancedauction.auth.model.TokenInfoModel
import com.cprt.advancedauction.auth.model.UserCredentials
import com.cprt.advancedauction.firebaseauth.data.requestBodyEntity.CredentialsRequestBody
import com.cprt.advancedauction.firebaseauth.data.responseEntity.TokenInfoEntity
import com.cprt.advancedauction.firebaseauth.data.service.AuthService
import com.cprt.advancedauction.firebaseauth.util.AuthUtil
import com.cprt.advancedauction.firebaseauth.util.TokenUpdateHelper

internal class FirebaseAuthentication(
    private val authTokenHolder: AuthTokenHolder,
    private val authUtil: AuthUtil,
    private val signInService: AuthService.SignIn,
    private val signUpService: AuthService.SignUp,
    private val refreshTokenService: AuthService.RefreshToken,
) : Authentication {

    private val tokenUpdateHelper by lazy {
        TokenUpdateHelper(
            signInService = signInService,
            refreshTokenService = refreshTokenService,
            authUtil = authUtil,
        )
    }

    override suspend fun signInWithEmail(userCredentials: UserCredentials, isGuest: Boolean) {
        val token = emailSignIn(userCredentials)

        authUtil.saveTokenIntoPreferences(token)

        if (isGuest) {
            authTokenHolder.setGuestState(
                tokenInfo = token
            )
        } else {
            authUtil.saveCredentialsIntoPreferences(userCredentials)

            authTokenHolder.setLoggedInState(
                tokenInfo = token,
                credentials = userCredentials
            )
        }
    }

    override suspend fun signInAnon() {
        val tokenInfoEntity: TokenInfoEntity = signUpService.load(CredentialsRequestBody())
        val token = TokenInfoModel(
            accessToken = tokenInfoEntity.idToken,
            refreshToken = tokenInfoEntity.refreshToken,
            expirationDate = tokenInfoEntity.expiresIn.toLong(),
        )

        authUtil.saveTokenIntoPreferences(
            tokenInfo = token
        )
        authTokenHolder.setAnonState(
            tokenInfo = token
        )
    }

    override suspend fun updateToken(): TokenInfoModel {
        return tokenUpdateHelper.updateToken(
            userLoginState = authTokenHolder.userLoginState.value
        )
    }

    override fun signOut() {
        authUtil.clearTokens()
        authUtil.clearCredentials()
    }

    private suspend fun emailSignIn(credentials: UserCredentials): TokenInfoModel {
        val tokenInfoEntity: TokenInfoEntity = signInService.load(
            CredentialsRequestBody(
                email = credentials.email,
                password = credentials.password
            )
        )

        return TokenInfoModel(
            accessToken = tokenInfoEntity.idToken,
            refreshToken = tokenInfoEntity.refreshToken,
            expirationDate = tokenInfoEntity.expiresIn.toLong(),
        )
    }
}
