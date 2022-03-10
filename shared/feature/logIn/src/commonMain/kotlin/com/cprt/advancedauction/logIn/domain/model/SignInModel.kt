package com.cprt.advancedauction.logIn.domain.model

import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel

internal data class SignInModel(
    val userCredentials: UserCredentialsModel,
    val isRemember: Boolean,
)
