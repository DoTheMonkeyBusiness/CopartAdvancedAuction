package com.cprt.advancedauction.logIn.domain.useCase

import com.cprt.advancedauction.core.screen.useCase.UseCase
import com.cprt.advancedauction.logIn.domain.model.SignInModel
import com.cprt.advancedauction.logIn.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers

internal class SignInUseCase(
    private val loginRepository: LoginRepository
) : UseCase<SignInModel, Unit>(Dispatchers.IO) {

    override suspend fun execute(parameters: SignInModel) {
        return loginRepository.signIn(parameters)
    }
}
