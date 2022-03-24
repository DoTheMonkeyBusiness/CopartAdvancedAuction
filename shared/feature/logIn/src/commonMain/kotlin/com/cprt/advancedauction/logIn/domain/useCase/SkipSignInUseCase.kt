package com.cprt.advancedauction.logIn.domain.useCase

import com.cprt.advancedauction.core.useCase.UseCase
import com.cprt.advancedauction.logIn.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers

internal class SkipSignInUseCase(
    private val loginRepository: LoginRepository
) : UseCase<Unit, Unit>(Dispatchers.IO) {

    override suspend fun execute(parameters: Unit) {
        return loginRepository.skipSignIn()
    }
}
