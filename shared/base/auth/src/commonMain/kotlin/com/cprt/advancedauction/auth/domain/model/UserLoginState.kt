package com.cprt.advancedauction.auth.domain.model

sealed class UserLoginState(
    open val tokenInfo: TokenInfoModel,
) {
    fun isTokenOutdated(
        secondsNow: Long
    ): Boolean = tokenInfo.expirationDate - secondsNow <= 0

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
