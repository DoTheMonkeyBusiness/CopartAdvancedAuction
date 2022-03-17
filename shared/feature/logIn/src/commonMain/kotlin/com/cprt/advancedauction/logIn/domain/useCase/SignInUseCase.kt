package com.cprt.advancedauction.logIn.domain.useCase

import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.core.screen.useCase.UseCase
import com.cprt.advancedauction.core.screen.utils.withoutWhiteSpaces
import com.cprt.advancedauction.logIn.domain.model.SignInModel
import com.cprt.advancedauction.logIn.domain.repository.LoginRepository
import com.cprt.advancedauction.logIn.utils.EmailValidator
import com.cprt.advancedauction.logIn.utils.SignInPasswordValidator
import kotlinx.coroutines.Dispatchers

internal class SignInUseCase(
    private val emailValidator: EmailValidator,
    private val loginRepository: LoginRepository,
    private val signInPasswordValidator: SignInPasswordValidator,
) : UseCase<SignInModel, Unit>(Dispatchers.IO) {

    override suspend fun execute(parameters: SignInModel) {
        val model = parameters.copy(
            userCredentials = UserCredentialsModel(
                email = parameters.userCredentials.email.withoutWhiteSpaces,
                password = parameters.userCredentials.password.withoutWhiteSpaces,
            )
        )

        emailValidator.validate(model.userCredentials.email)
        signInPasswordValidator.validate(model.userCredentials.password)

        return loginRepository.signIn(model)
    }
}
