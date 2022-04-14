package com.cprt.advancedauction.navigation.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.cprt.advancedauction.core.ScreenSize
import com.cprt.advancedauction.core.tools.LocalScreenSize

sealed class TwoPaneScreen : Screen {

    protected val isInTwoPaneMode: Boolean
        @Composable
        get() = LocalScreenSize.current == ScreenSize.Large
}
