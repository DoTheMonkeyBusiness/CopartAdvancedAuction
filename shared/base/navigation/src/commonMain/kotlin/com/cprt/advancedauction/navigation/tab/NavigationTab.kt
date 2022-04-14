package com.cprt.advancedauction.navigation.tab

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cprt.advancedauction.navigation.navigator.AANavigator

abstract class NavigationTab : Tab {

    final override val options: TabOptions
        @Composable get() = getOptions(false)

    abstract val screen: Screen

    @Composable
    abstract fun getOptions(isSelected: Boolean): TabOptions

    @Composable
    final override fun Content() {
        AANavigator(screen)
    }
}
