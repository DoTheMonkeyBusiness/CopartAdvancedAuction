package com.cprt.advancedauction.logIn.domain.useCase

import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.core.screen.useCase.UseCase
import com.cprt.advancedauction.core.screen.utils.withoutWhiteSpaces
import com.cprt.advancedauction.logIn.domain.model.SignUpModel
import com.cprt.advancedauction.logIn.domain.repository.LoginRepository
import com.cprt.advancedauction.logIn.utils.EmailValidator
import com.cprt.advancedauction.logIn.utils.SignUpPasswordValidator
import kotlinx.coroutines.Dispatchers

internal class SignUpUseCase(
    private val emailValidator: EmailValidator,
    private val loginRepository: LoginRepository,
    private val signUpPasswordValidator: SignUpPasswordValidator,
) : UseCase<SignUpModel, Unit>(Dispatchers.IO) {

    override suspend fun execute(parameters: SignUpModel) {
        val email = parameters.email.withoutWhiteSpaces
        val password = parameters.password.withoutWhiteSpaces
        val repeatedPassword = parameters.repeatedPassword.withoutWhiteSpaces

        emailValidator.validate(email)
        signUpPasswordValidator.validate(password to repeatedPassword)

        return loginRepository.signUp(
            userCredentials = UserCredentialsModel(
                email = email,
                password = password
            )
        )
    }
}
