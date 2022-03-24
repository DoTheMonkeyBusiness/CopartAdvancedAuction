package com.cprt.advancedauction.core.tools

interface ScreenProvider {

    val logInScreen: LogInScreen

    val mainScreen: MainScreen

    val onBoardingScreen: OnBoardingScreen

    val registrationScreen: RegistrationScreen

    val forgotPasswordScreen: ForgotPasswordScreen

    val splashScreen: SplashScreen
}
