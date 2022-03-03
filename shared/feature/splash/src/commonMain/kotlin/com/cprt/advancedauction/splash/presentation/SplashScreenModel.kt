package com.cprt.advancedauction.splash.presentation

import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.core.screen.screenModel.AAScreenModel
import com.cprt.advancedauction.core.screen.tools.ScreenProvider
import com.cprt.advancedauction.core.screen.useCase.ResultOf
import com.cprt.advancedauction.splash.domain.useCase.IsNeedToLoginUseCase
import kotlinx.coroutines.launch

internal class SplashScreenModel(
    private val screenProvider: ScreenProvider,
    private val isNeedToLoginUseCase: IsNeedToLoginUseCase,
) : AAScreenModel<SplashScreenModel.State>(State.Initial) {

    fun onInit() {
        if (state != State.Initial) return
        state = State.Idle
        coroutineScope.launch {
            val useCase = isNeedToLoginUseCase(Unit)
            val isNeedToLogin = useCase is ResultOf.Success && useCase.value

            state = if (isNeedToLogin) {
                State.GoLogin
            } else {
                State.GoHome
            }
        }
    }

    fun goHome(navigator: Navigator) {
        navigator.replace(screenProvider.mainScreen)
    }

    fun goLogin(navigator: Navigator) {
        navigator.replace(screenProvider.logInScreen)
    }

    sealed class State {
        object Initial : State()
        object Idle : State()
        object GoHome : State()
        object GoLogin : State()
    }
}