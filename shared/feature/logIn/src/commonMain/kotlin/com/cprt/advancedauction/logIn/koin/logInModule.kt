package com.cprt.advancedauction.logIn.koin

import com.cprt.advancedauction.logIn.data.repository.LoginRepositoryImpl
import com.cprt.advancedauction.logIn.data.repository.PasswordResetRepositoryImpl
import com.cprt.advancedauction.logIn.domain.repository.LoginRepository
import com.cprt.advancedauction.logIn.domain.repository.PasswordResetRepository
import com.cprt.advancedauction.logIn.domain.useCase.SendResetEmailUseCase
import com.cprt.advancedauction.logIn.domain.useCase.SignInUseCase
import com.cprt.advancedauction.logIn.domain.useCase.SignUpUseCase
import com.cprt.advancedauction.logIn.domain.useCase.SkipSignInUseCase
import com.cprt.advancedauction.logIn.presentation.login.LoginScreenModel
import com.cprt.advancedauction.logIn.presentation.registration.RegistrationScreenModel
import com.cprt.advancedauction.logIn.presentation.resetPassword.ForgotPasswordScreenModel
import org.koin.dsl.module

val logInModule = module {
    factory {
        LoginScreenModel(
            internalNotificationManager = get(),
            loginErrorString = get(),
            screenProvider = get(),
            signInUseCase = get(),
            skipSignInUseCase = get(),
        )
    }
    factory {
        RegistrationScreenModel(
            internalNotificationManager = get(),
            loginErrorString = get(),
            signUpUseCase = get(),
            screenProvider = get(),
        )
    }
    factory {
        ForgotPasswordScreenModel(
            internalNotificationManager = get(),
            loginErrorString = get(),
            screenProvider = get(),
            sendResetCodeUseCase = get(),
        )
    }
    factory {
        SendResetEmailUseCase(
            passwordResetRepository = get(),
        )
    }
    factory {
        SignInUseCase(
            loginRepository = get()
        )
    }
    factory {
        SignUpUseCase(
            loginRepository = get()
        )
    }
    factory {
        SkipSignInUseCase(
            loginRepository = get()
        )
    }

    factory<LoginRepository> {
        LoginRepositoryImpl(
            authenticator = get()
        )
    }
    factory<PasswordResetRepository> {
        PasswordResetRepositoryImpl(
            passwordRestorer = get(),
        )
    }
}
