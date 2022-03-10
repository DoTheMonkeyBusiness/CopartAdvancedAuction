package com.cprt.advancedauction.auth.domain.model

import kotlinx.datetime.Clock

sealed class UserLoginState(
    open val tokenInfo: TokenInfoModel,
) {
    val isTokenOutdated: Boolean
        get() {
            val clock = Clock.System.now()
            val secondsNow = clock.epochSeconds

            return tokenInfo.expirationDate - secondsNow <= 0
        }

    data class Anon(
        override val tokenInfo: TokenInfoModel,
    ) : UserLoginState(tokenInfo)

    data class LoggedIn(
        override val tokenInfo: TokenInfoModel,
        val userCredentials: UserCredentialsModel,
    ) : UserLoginState(tokenInfo)

    data class Guest(
        override val tokenInfo: TokenInfoModel,
    ) : UserLoginState(tokenInfo)
}
