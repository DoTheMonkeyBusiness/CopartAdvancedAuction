package com.cprt.advancedauction.navigation.navigator

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.navigation.transition.AAScreenTransition

@Composable
fun AANavigator(screen: Screen) {
    Navigator(screen) { navigator ->
        AAScreenTransition(navigator = navigator) { screen ->
            screen.Content()
        }
    }
}
