package com.cprt.advancedauction.navigation.tools

interface ScreenProvider {

    val logInScreen: LogInScreen

    val mainScreen: MainScreen

    val onBoardingScreen: OnBoardingScreen

    val registrationScreen: RegistrationScreen

    val forgotPasswordScreen: ForgotPasswordScreen

    val splashScreen: SplashScreen

    val vehicleSearchScreen: VehicleSearchScreen
}
