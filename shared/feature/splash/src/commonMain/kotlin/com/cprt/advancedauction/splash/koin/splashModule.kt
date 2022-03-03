package com.cprt.advancedauction.splash.koin

import com.cprt.advancedauction.splash.data.repository.LoginRepositoryImpl
import com.cprt.advancedauction.splash.domain.repository.LoginRepository
import com.cprt.advancedauction.splash.domain.useCase.IsNeedToLoginUseCase
import com.cprt.advancedauction.splash.presentation.SplashScreenModel
import org.koin.dsl.module

val splashModule = module {
    factory {
        SplashScreenModel(
            screenProvider = get(),
            isNeedToLoginUseCase = get(),
        )
    }
    factory<LoginRepository> {
        LoginRepositoryImpl(
            authentication = get(),
            authTokenHolder = get(),
        )
    }
    factory {
        IsNeedToLoginUseCase(
            loginRepository = get()
        )
    }
}
