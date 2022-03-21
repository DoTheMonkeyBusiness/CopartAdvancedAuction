package com.cprt.advancedauction.accountSettings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cprt.advancedauction.navigation.tab.NavigationTab

object AccountSettingsTab : NavigationTab {

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

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Settings")
        }
    }
}
