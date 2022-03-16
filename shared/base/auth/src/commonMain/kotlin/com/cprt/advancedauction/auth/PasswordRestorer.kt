package com.cprt.advancedauction.auth

interface PasswordRestorer {

    suspend fun sendPasswordResetEmail(
        email: String,
    )
}
