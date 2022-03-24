package com.cprt.advancedauction.firebaseauth

import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.auth.domain.model.TokenInfoModel
import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.auth.domain.model.UserLoginState
import com.cprt.advancedauction.core.AppPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class FirebaseAuthTokenHolder(
    private val defaultPreferences: AppPreferences.Default,
    private val securedPreferences: AppPreferences.Secured,
) : AuthTokenHolder {

    private val _userLoginState = MutableStateFlow(getUserState())
    override val userLoginState: StateFlow<UserLoginState> = _userLoginState

    override fun setAnonState(tokenInfo: TokenInfoModel) {
        defaultPreferences.isAnonUser = true
        _userLoginState.value = UserLoginState.Anon(
            tokenInfo = tokenInfo
        )
    }

    override fun setLoggedInState(tokenInfo: TokenInfoModel, credentials: UserCredentialsModel) {
        defaultPreferences.isAnonUser = false
        _userLoginState.value = UserLoginState.LoggedIn(
            tokenInfo = tokenInfo,
            userCredentials = credentials
        )
    }

    override fun setGuestState(tokenInfo: TokenInfoModel) {
        defaultPreferences.isAnonUser = false
        _userLoginState.value = UserLoginState.Guest(
            tokenInfo = tokenInfo
        )
    }

    private fun getUserState(): UserLoginState {
        val tokenInfo = TokenInfoModel(
            accessToken = securedPreferences.accessToken,
            refreshToken = securedPreferences.refreshToken,
            expirationDate = defaultPreferences.tokenExpirationDate,
        )
        val userCredentials = UserCredentialsModel(
            email = securedPreferences.email,
            password = securedPreferences.password,
        )


        return when {
            defaultPreferences.isAnonUser -> UserLoginState.Anon(
                tokenInfo = tokenInfo
            )
            userCredentials.email.isNotEmpty() && userCredentials.password.isNotEmpty() -> UserLoginState.LoggedIn(
                tokenInfo = tokenInfo,
                userCredentials = userCredentials
            )
            else -> UserLoginState.Guest(tokenInfo)
        }
    }
}
