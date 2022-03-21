package com.cprt.advancedauction.navigation.tab

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

interface NavigationTab : Tab {

    override val options: TabOptions
        @Composable get() = getOptions(false)

    @Composable
    fun getOptions(isSelected: Boolean): TabOptions
}
