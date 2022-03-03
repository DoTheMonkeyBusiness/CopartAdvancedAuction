package com.cprt.advancedauction.auth

import com.cprt.advancedauction.auth.model.TokenInfoModel
import com.cprt.advancedauction.auth.model.UserCredentials
import com.cprt.advancedauction.auth.model.UserLoginState
import kotlinx.coroutines.flow.StateFlow

interface AuthTokenHolder {

    val userLoginState: StateFlow<UserLoginState>

    fun setAnonState(tokenInfo: TokenInfoModel)

    fun setLoggedInState(
        tokenInfo: TokenInfoModel,
        credentials: UserCredentials,
    )

    fun setGuestState(tokenInfo: TokenInfoModel)
}
