package com.cprt.advancedauction.logIn.koin

import com.cprt.advancedauction.logIn.data.repository.LoginRepositoryImpl
import com.cprt.advancedauction.logIn.domain.repository.LoginRepository
import com.cprt.advancedauction.logIn.domain.useCase.SignInUseCase
import com.cprt.advancedauction.logIn.domain.useCase.SkipSignInUseCase
import com.cprt.advancedauction.logIn.presentation.login.LoginScreenModel
import org.koin.dsl.module

val logInModule = module {
    factory {
        LoginScreenModel(
            loginErrorString = get(),
            screenProvider = get(),
            signInUseCase = get(),
            skipSignInUseCase = get(),
        )
    }
    factory {
        SignInUseCase(
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
