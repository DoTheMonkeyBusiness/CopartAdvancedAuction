package com.cprt.advancedauction.common

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.core.screen.tools.LocalScreenSize
import com.cprt.advancedauction.core.screen.utils.calculateScreenSize
import com.cprt.advancedauction.splash.presentation.SplashScreenUI
import com.cprt.advancedauction.theme.AppMaterialTheme

@Composable
fun App() {
    AppMaterialTheme {
        BoxWithConstraints {
            CompositionLocalProvider(
                LocalScreenSize provides calculateScreenSize(maxWidth)
            ) {
                Navigator(SplashScreenUI())
            }
        }
    }
}