package com.cprt.advancedauction.firebaseauth

import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.auth.Authentication
import com.cprt.advancedauction.auth.domain.model.TokenInfoModel
import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.core.screen.utils.Mapper
import com.cprt.advancedauction.firebaseauth.data.requestBodyEntity.CredentialsRequestBody
import com.cprt.advancedauction.firebaseauth.data.responseEntity.*
import com.cprt.advancedauction.firebaseauth.data.service.AuthService
import com.cprt.advancedauction.firebaseauth.exception.LoginException
import com.cprt.advancedauction.firebaseauth.util.AuthUtil
import com.cprt.advancedauction.firebaseauth.util.TokenUpdateHelper
import com.cprt.advancedauction.firebaseauth.util.mapper.AnonSignInErrorMapper
import com.cprt.advancedauction.firebaseauth.util.mapper.SignInErrorMapper
import com.cprt.advancedauction.firebaseauth.util.mapper.SignUpErrorMapper
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
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

    override fun signOut() {
        authUtil.clearTokens()
        authUtil.clearCredentials()
    }

    private suspend fun registration(
        userCredentials: UserCredentialsModel
    ): TokenInfoModel {
        val tokenInfoEntity: TokenInfoEntity = runCatching {
            signUpService.load(
                CredentialsRequestBody(
                    email = userCredentials.email,
                    password = userCredentials.password,
                )
            )
        }.getOrElse {
            processLoginException<SignUpErrorCode>(
                exception = it,
                mapper = signUpErrorMapper
            )
        }

        return getTokenInfoModel(tokenInfoEntity)
    }

    private suspend fun anonSignIn(): TokenInfoModel {
        val tokenInfoEntity: TokenInfoEntity = runCatching {
            signUpService.load(CredentialsRequestBody())
        }.getOrElse {
            processLoginException<AnonErrorCode>(
                exception = it,
                mapper = anonSignInErrorMapper
            )
        }

        return getTokenInfoModel(tokenInfoEntity)
    }

    private suspend fun emailSignIn(credentials: UserCredentialsModel): TokenInfoModel {
        val tokenInfoEntity: TokenInfoEntity = runCatching {
            signInService.load(
                CredentialsRequestBody(
                    email = credentials.email,
                    password = credentials.password
                )
            )
        }.getOrElse {
            processLoginException<SignInErrorCode>(
                exception = it,
                mapper = loginErrorMapper
            )
        }

        return getTokenInfoModel(tokenInfoEntity)
    }

    private fun getTokenInfoModel(
        tokenInfoEntity: TokenInfoEntity
    ): TokenInfoModel = TokenInfoModel(
        accessToken = tokenInfoEntity.idToken ?: "",
        refreshToken = tokenInfoEntity.refreshToken ?: "",
        expirationDate = tokenInfoEntity.expiresIn?.toLong() ?: 0L,
    )

    private suspend inline fun <reified T> processLoginException(
        exception: Throwable,
        mapper: Mapper<T?, String>
    ): Nothing {
        if (exception !is ClientRequestException) {
            throw exception
        }

        val exceptionResponse = exception.response
        val exceptionEntity = json.decodeFromString<LoginErrorEntity<T>>(
            exceptionResponse.bodyAsText()
        )
        val loginErrorMessage = mapper.map(exceptionEntity.error?.errorCode)

        throw LoginException(
            exceptionMessage = exception.message,
            errorMessage = loginErrorMessage
        )
    }
}
