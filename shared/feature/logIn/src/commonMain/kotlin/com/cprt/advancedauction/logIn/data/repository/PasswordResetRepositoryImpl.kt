package com.cprt.advancedauction.logIn.data.repository

import com.cprt.advancedauction.auth.PasswordRestorer
import com.cprt.advancedauction.logIn.domain.repository.PasswordResetRepository

internal class PasswordResetRepositoryImpl(
    private val passwordRestorer: PasswordRestorer,
) : PasswordResetRepository {

    override suspend fun sendEmail(email: String) {
        passwordRestorer.sendPasswordResetEmail(
            email = email
        )
    }
}
