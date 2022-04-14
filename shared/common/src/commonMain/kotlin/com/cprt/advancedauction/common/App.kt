package com.cprt.advancedauction.common

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.cprt.advancedauction.core.tools.LocalScreenSize
import com.cprt.advancedauction.core.utils.calculateScreenSize
import com.cprt.advancedauction.internalNotification.presentation.NotificationListUI
import com.cprt.advancedauction.navigation.navigator.AANavigator
import com.cprt.advancedauction.splash.presentation.SplashScreenUI
import com.cprt.advancedauction.theme.AppMaterialTheme

@Composable
fun App() {
    AppMaterialTheme {
        BoxWithConstraints {
            CompositionLocalProvider(
                LocalScreenSize provides calculateScreenSize(maxWidth)
            ) {
                AANavigator(SplashScreenUI())
                NotificationListUI()
            }
        }
    }
}