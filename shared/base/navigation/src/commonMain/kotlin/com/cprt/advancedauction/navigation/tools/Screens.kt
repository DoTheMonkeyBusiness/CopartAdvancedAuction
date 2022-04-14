package com.cprt.advancedauction.navigation.tools

import cafe.adriel.voyager.core.screen.Screen
import com.cprt.advancedauction.navigation.screen.TwoPaneMainScreen

interface LogInScreen : Screen
interface MainScreen : Screen
interface OnBoardingScreen : Screen
interface RegistrationScreen : Screen
interface ForgotPasswordScreen : Screen
interface SplashScreen : Screen

abstract class VehicleSearchScreen : TwoPaneMainScreen()
