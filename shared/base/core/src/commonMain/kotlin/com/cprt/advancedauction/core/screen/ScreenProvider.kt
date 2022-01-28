package com.cprt.advancedauction.core.screen

interface ScreenProvider {

    val logInScreen: LogInScreen

    val mainScreen: MainScreen

    val onBoardingScreen: OnBoardingScreen

    val splashScreen: SplashScreen
}
