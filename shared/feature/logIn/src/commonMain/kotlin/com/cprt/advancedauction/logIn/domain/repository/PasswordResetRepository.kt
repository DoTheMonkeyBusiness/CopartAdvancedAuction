package com.cprt.advancedauction.logIn.domain.repository

internal interface PasswordResetRepository {

    suspend fun sendEmail(
        email: String
    )
}
