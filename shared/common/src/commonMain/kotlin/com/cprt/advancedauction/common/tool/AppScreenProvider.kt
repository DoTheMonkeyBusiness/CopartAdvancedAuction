package com.cprt.advancedauction.common.tool

import com.cprt.advancedauction.core.screen.tools.*
import com.cprt.advancedauction.logIn.presentation.LogInScreenUI
import com.cprt.advancedauction.main.presentation.MainScreenUI
import com.cprt.advancedauction.onBoarding.presentation.OnBoardingScreenUI
import com.cprt.advancedauction.splash.presentation.SplashScreenUI

internal class AppScreenProvider : ScreenProvider {

    override val logInScreen: LogInScreen
        get() = LogInScreenUI()
    override val mainScreen: MainScreen
        get() = MainScreenUI()
    override val onBoardingScreen: OnBoardingScreen
        get() = OnBoardingScreenUI()
    override val splashScreen: SplashScreen
        get() = SplashScreenUI()
}
