package com.cprt.advancedauction.auth

import com.cprt.advancedauction.auth.domain.model.TokenInfoModel
import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.auth.domain.model.UserLoginState
import kotlinx.coroutines.flow.StateFlow

interface AuthTokenHolder {

    val userLoginState: StateFlow<UserLoginState>

    fun setAnonState(tokenInfo: TokenInfoModel)

    fun setLoggedInState(
        tokenInfo: TokenInfoModel,
        credentials: UserCredentialsModel,
    )

    fun setGuestState(tokenInfo: TokenInfoModel)
}
