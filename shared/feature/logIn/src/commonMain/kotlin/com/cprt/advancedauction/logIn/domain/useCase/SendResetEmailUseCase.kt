package com.cprt.advancedauction.logIn.domain.useCase

import com.cprt.advancedauction.core.screen.useCase.UseCase
import com.cprt.advancedauction.logIn.domain.repository.PasswordResetRepository
import kotlinx.coroutines.Dispatchers

internal class SendResetEmailUseCase(
    private val passwordResetRepository: PasswordResetRepository
) : UseCase<String, Unit>(Dispatchers.IO){

    override suspend fun execute(parameters: String) {
        passwordResetRepository.sendEmail(parameters)
    }
}
