package com.cprt.advancedauction.logIn.koin

import com.cprt.advancedauction.logIn.data.repository.LoginRepositoryImpl
import com.cprt.advancedauction.logIn.domain.repository.LoginRepository
import com.cprt.advancedauction.logIn.domain.useCase.SignInUseCase
import com.cprt.advancedauction.logIn.domain.useCase.SignUpUseCase
import com.cprt.advancedauction.logIn.domain.useCase.SkipSignInUseCase
import com.cprt.advancedauction.logIn.presentation.login.LoginScreenModel
import com.cprt.advancedauction.logIn.presentation.registration.RegistrationScreenModel
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
}
