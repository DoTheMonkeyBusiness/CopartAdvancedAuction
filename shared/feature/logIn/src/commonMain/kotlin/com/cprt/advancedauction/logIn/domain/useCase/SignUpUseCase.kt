package com.cprt.advancedauction.logIn.domain.useCase

import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.core.screen.useCase.UseCase
import com.cprt.advancedauction.logIn.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers

internal class SignUpUseCase(
    private val loginRepository: LoginRepository
) : UseCase<UserCredentialsModel, Unit>(Dispatchers.IO) {

    override suspend fun execute(parameters: UserCredentialsModel) {
        return loginRepository.signUp(parameters)
    }
}
