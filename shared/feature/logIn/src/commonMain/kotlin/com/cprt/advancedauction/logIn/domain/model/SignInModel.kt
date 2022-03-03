package com.cprt.advancedauction.logIn.domain.model

import com.cprt.advancedauction.auth.model.UserCredentials

internal data class SignInModel(
    val userCredentials: UserCredentials,
    val isRemember: Boolean,
)
