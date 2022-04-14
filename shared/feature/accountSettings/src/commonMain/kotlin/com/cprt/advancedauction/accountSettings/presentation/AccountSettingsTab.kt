package com.cprt.advancedauction.accountSettings.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cprt.advancedauction.navigation.tab.NavigationTab

object AccountSettingsTab : NavigationTab() {

    override val screen: Screen
        get() = AccountSettingsScreen()

    @Composable
    override fun getOptions(isSelected: Boolean): TabOptions {
        val vector = if (isSelected) Icons.Filled.Person else Icons.Outlined.Person
        val icon = rememberVectorPainter(vector)

        return remember(icon) {
            TabOptions(
                index = 2u,
                title = "Settings",
                icon = icon
            )
        }
    }
}
