package com.cprt.advancedauction.splash.data.repository

import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.auth.Authentication
import com.cprt.advancedauction.auth.domain.model.UserLoginState
import com.cprt.advancedauction.dateTime.DateTime
import com.cprt.advancedauction.splash.domain.repository.LoginRepository

internal class LoginRepositoryImpl(
    private val authentication: Authentication,
    private val authTokenHolder: AuthTokenHolder,
    private val dateTime: DateTime,
) : LoginRepository {

    override suspend fun isNeedToLogin(): Boolean {
        return when (val state = authTokenHolder.userLoginState.value) {
            is UserLoginState.Anon,
            is UserLoginState.LoggedIn -> !isUserFresh(state)
            else -> true
        }
    }

    private suspend fun isUserFresh(state: UserLoginState): Boolean {
        if (!state.isTokenOutdated(
                secondsNow = dateTime.secondsCurrent
            )
        ) return true

        return runCatching {
            authentication.updateToken()

            true
        }.getOrDefault(false)
    }
}