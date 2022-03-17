package com.cprt.advancedauction.logIn.domain.useCase

import com.cprt.advancedauction.core.screen.useCase.UseCase
import com.cprt.advancedauction.core.screen.utils.withoutWhiteSpaces
import com.cprt.advancedauction.logIn.domain.repository.PasswordResetRepository
import com.cprt.advancedauction.logIn.utils.EmailValidator
import kotlinx.coroutines.Dispatchers

internal class SendResetEmailUseCase(
    private val emailValidator: EmailValidator,
    private val passwordResetRepository: PasswordResetRepository
) : UseCase<String, Unit>(Dispatchers.IO){

    override suspend fun execute(parameters: String) {
        val email = parameters.withoutWhiteSpaces

        emailValidator.validate(email)

        passwordResetRepository.sendEmail(email)
    }
}
