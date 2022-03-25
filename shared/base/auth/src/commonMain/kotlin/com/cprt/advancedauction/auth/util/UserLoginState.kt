package com.cprt.advancedauction.auth.util

import com.cprt.advancedauction.auth.domain.model.UserLoginState

val UserLoginState.isLoggedIn: Boolean
    get() = this !is UserLoginState.Anon
