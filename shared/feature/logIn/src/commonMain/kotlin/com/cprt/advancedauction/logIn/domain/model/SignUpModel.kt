package com.cprt.advancedauction.logIn.domain.model

internal data class SignUpModel(
    val email: String,
    val password: String,
    val repeatedPassword: String,
)
