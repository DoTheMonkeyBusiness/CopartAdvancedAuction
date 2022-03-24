package com.cprt.advancedauction.firebaseauth.util

import com.cprt.advancedauction.auth.domain.model.TokenInfoModel
import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.core.AppPreferences
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
        credentials: UserCredentialsModel,
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
