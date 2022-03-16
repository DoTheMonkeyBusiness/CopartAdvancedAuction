package com.cprt.advancedauction.common.tool

import com.cprt.advancedauction.core.screen.tools.*
import com.cprt.advancedauction.logIn.presentation.login.LogInScreenUI
import com.cprt.advancedauction.logIn.presentation.registration.RegistrationScreenUI
import com.cprt.advancedauction.logIn.presentation.resetPassword.ForgotPasswordScreenUI
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
    override val registrationScreen: RegistrationScreen
        get() = RegistrationScreenUI()
    override val forgotPasswordScreen: ForgotPasswordScreen
        get() = ForgotPasswordScreenUI()
    override val splashScreen: SplashScreen
        get() = SplashScreenUI()
}
