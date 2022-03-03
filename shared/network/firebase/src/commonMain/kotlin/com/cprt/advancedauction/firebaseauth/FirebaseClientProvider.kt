package com.cprt.advancedauction.firebaseauth

import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.firebaseauth.data.service.RefreshTokenService
import com.cprt.advancedauction.firebaseauth.data.service.SignInService
import com.cprt.advancedauction.firebaseauth.util.AuthUtil
import com.cprt.advancedauction.firebaseauth.util.Constants.Url.IDENTITYTOOLKIT_HOST_URL
import com.cprt.advancedauction.firebaseauth.util.Constants.Url.SECURETOKEN_HOST_URL
import com.cprt.advancedauction.firebaseauth.util.TokenUpdateHelper
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


internal class FirebaseClientProvider(
    private val authTokenHolder: AuthTokenHolder,
    private val authUtil: AuthUtil,
) {

    fun getHttpClient() = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(Auth) {
            bearer {
                loadTokens {
                    val tokenInfo = authTokenHolder
                        .userLoginState
                        .value
                        .tokenInfo

                    BearerTokens(
                        accessToken = tokenInfo.accessToken,
                        refreshToken = tokenInfo.refreshToken,
                    )
                }
                refreshTokens {
                    val tokenUpdateHelper = TokenUpdateHelper(
                        signInService = SignInService(client = client),
                        refreshTokenService = RefreshTokenService(client = client),
                        authUtil = authUtil
                    )
                    val tokenInfo = tokenUpdateHelper.updateToken(
                        userLoginState = authTokenHolder.userLoginState.value
                    )

                    BearerTokens(
                        accessToken = tokenInfo.accessToken,
                        refreshToken = tokenInfo.refreshToken,
                    )
                }
                sendWithoutRequest { request ->
                    request.url.host != IDENTITYTOOLKIT_HOST_URL
                            && request.url.host != SECURETOKEN_HOST_URL
                }
            }
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("KTOR Firebase: $message")
                }
            }
        }
    }
}
