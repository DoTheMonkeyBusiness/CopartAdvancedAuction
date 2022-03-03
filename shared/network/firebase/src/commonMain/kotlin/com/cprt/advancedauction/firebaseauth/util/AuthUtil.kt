package com.cprt.advancedauction.firebaseauth.util

import com.cprt.advancedauction.auth.model.TokenInfoModel
import com.cprt.advancedauction.auth.model.UserCredentials
import com.cprt.advancedauction.core.screen.AppPreferences
import kotlinx.datetime.Clock

internal class AuthUtil(
    private val defaultPreferences: AppPreferences.Default,
    private val securedPreferences: AppPreferences.Secured,
) {

    fun saveTokenIntoPreferences(
        tokenInfo: TokenInfoModel,
    ) {
        val clock = Clock.System.now()

        securedPreferences.accessToken = tokenInfo.accessToken
        securedPreferences.accessToken = tokenInfo.accessToken
        defaultPreferences.tokenExpirationDate = clock.epochSeconds + tokenInfo.expirationDate
    }

    fun saveCredentialsIntoPreferences(
        credentials: UserCredentials,
    ) {
        securedPreferences.email = credentials.email
        securedPreferences.password = credentials.password
    }

    fun clearTokens() {
        securedPreferences.accessToken = ""
        securedPreferences.refreshToken = ""
        defaultPreferences.tokenExpirationDate = 0L
    }

    fun clearCredentials() {
        securedPreferences.email = ""
        securedPreferences.password = ""
    }
}
