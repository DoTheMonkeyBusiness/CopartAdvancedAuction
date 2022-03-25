package com.cprt.advancedauction.firebaseauth

import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.auth.Authentication
import com.cprt.advancedauction.auth.domain.model.TokenInfoModel
import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.firebaseauth.data.requestEntity.CredentialsRequestEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.AnonErrorCode
import com.cprt.advancedauction.firebaseauth.data.responseEntity.SignInErrorCode
import com.cprt.advancedauction.firebaseauth.data.responseEntity.SignUpErrorCode
import com.cprt.advancedauction.firebaseauth.data.responseEntity.TokenInfoResponseEntity
import com.cprt.advancedauction.firebaseauth.data.service.auth.AuthService
import com.cprt.advancedauction.firebaseauth.util.AuthUtil
import com.cprt.advancedauction.firebaseauth.util.TokenUpdateHelper
import com.cprt.advancedauction.firebaseauth.util.mapper.AnonSignInErrorMapper
import com.cprt.advancedauction.firebaseauth.util.mapper.SignInErrorMapper
import com.cprt.advancedauction.firebaseauth.util.mapper.SignUpErrorMapper
import com.cprt.advancedauction.firebaseauth.util.processLoginException
import kotlinx.serialization.json.Json

internal class FirebaseAuthentication(
    private val authTokenHolder: AuthTokenHolder,
    private val authUtil: AuthUtil,
    private val json: Json,
    private val anonSignInErrorMapper: AnonSignInErrorMapper,
    private val loginErrorMapper: SignInErrorMapper,
    private val signUpErrorMapper: SignUpErrorMapper,
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

    override suspend fun signInWithEmail(userCredentials: UserCredentialsModel, isGuest: Boolean) {
        val token = emailSignIn(userCredentials)

        authUtil.saveTokenIntoPreferences(
            tokenInfo = token
        )

        if (isGuest) {
            authTokenHolder.setGuestState(
                tokenInfo = token
            )
        } else {
            authUtil.saveCredentialsIntoPreferences(
                credentials = userCredentials
            )
            authTokenHolder.setLoggedInState(
                tokenInfo = token,
                credentials = userCredentials
            )
        }
    }

    override suspend fun signInAnon() {
        val token = anonSignIn()

        authUtil.saveTokenIntoPreferences(
            tokenInfo = token
        )
        authTokenHolder.setAnonState(
            tokenInfo = token
        )
    }

    override suspend fun signUp(
        userCredentials: UserCredentialsModel
    ) {
        val token = registration(userCredentials)

        authUtil.saveTokenIntoPreferences(
            tokenInfo = token,
        )
        authUtil.saveCredentialsIntoPreferences(
            credentials = userCredentials
        )
        authTokenHolder.setLoggedInState(
            tokenInfo = token,
            credentials = userCredentials,
        )
    }

    override suspend fun updateToken(): TokenInfoModel {
        return tokenUpdateHelper.updateToken(
            userLoginState = authTokenHolder.userLoginState.value
        )
    }

    override suspend fun signOut() {
        authUtil.clearTokens()
        authUtil.clearCredentials()

        signInAnon()
    }

    private suspend fun registration(
        userCredentials: UserCredentialsModel
    ): TokenInfoModel {
        val tokenInfoEntity: TokenInfoResponseEntity = runCatching {
            signUpService.load(
                CredentialsRequestEntity(
                    email = userCredentials.email,
                    password = userCredentials.password,
                )
            )
        }.getOrElse {
            it.processLoginException<SignUpErrorCode>(
                json = json,
                mapper = signUpErrorMapper
            )
        }

        return getTokenInfoModel(tokenInfoEntity)
    }

    private suspend fun anonSignIn(): TokenInfoModel {
        val tokenInfoEntity: TokenInfoResponseEntity = runCatching {
            signUpService.load(CredentialsRequestEntity())
        }.getOrElse {
            it.processLoginException<AnonErrorCode>(
                json = json,
                mapper = anonSignInErrorMapper
            )
        }

        return getTokenInfoModel(tokenInfoEntity)
    }

    private suspend fun emailSignIn(credentials: UserCredentialsModel): TokenInfoModel {
        val tokenInfoEntity: TokenInfoResponseEntity = runCatching {
            signInService.load(
                CredentialsRequestEntity(
                    email = credentials.email,
                    password = credentials.password
                )
            )
        }.getOrElse {
            it.processLoginException<SignInErrorCode>(
                json = json,
                mapper = loginErrorMapper
            )
        }

        return getTokenInfoModel(tokenInfoEntity)
    }

    private fun getTokenInfoModel(
        tokenInfoEntity: TokenInfoResponseEntity
    ): TokenInfoModel = TokenInfoModel(
        accessToken = tokenInfoEntity.idToken ?: "",
        refreshToken = tokenInfoEntity.refreshToken ?: "",
        expirationDate = tokenInfoEntity.expiresIn?.toLong() ?: 0L,
    )
}
