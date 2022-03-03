package com.cprt.advancedauction.splash.data.repository

import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.auth.Authentication
import com.cprt.advancedauction.auth.model.UserLoginState
import com.cprt.advancedauction.splash.domain.repository.LoginRepository

internal class LoginRepositoryImpl(
    private val authentication: Authentication,
    private val authTokenHolder: AuthTokenHolder
) : LoginRepository {

    override suspend fun isNeedToLogin(): Boolean {
        return when (val state = authTokenHolder.userLoginState.value) {
            is UserLoginState.Anon,
            is UserLoginState.LoggedIn -> !isUserFresh(state)
            else -> true
        }
    }

    private suspend fun isUserFresh(state: UserLoginState): Boolean {
        if (!state.isTokenOutdated) return true

        return runCatching {
            authentication.updateToken()

            true
        }.getOrDefault(false)
    }
}