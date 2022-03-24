package com.cprt.advancedauction.splash.domain.useCase

import com.cprt.advancedauction.core.useCase.UseCase
import com.cprt.advancedauction.splash.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers

internal class IsNeedToLoginUseCase(
    private val loginRepository: LoginRepository
) : UseCase<Unit, Boolean>(Dispatchers.IO) {

    override suspend fun execute(parameters: Unit): Boolean {
        return loginRepository.isNeedToLogin()
    }
}
