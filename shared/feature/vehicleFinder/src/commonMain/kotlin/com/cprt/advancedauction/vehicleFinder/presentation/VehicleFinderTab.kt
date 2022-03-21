package com.cprt.advancedauction.vehicleFinder.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cprt.advancedauction.navigation.tab.NavigationTab

object VehicleFinderTab : NavigationTab {

    @Composable
    override fun getOptions(isSelected: Boolean): TabOptions {
        val vector = if (isSelected) Icons.Filled.DirectionsCar else Icons.Outlined.DirectionsCar
        val icon = rememberVectorPainter(vector)

        return remember(icon) {
            TabOptions(
                index = 0u,
                title = "Vehicle Finder",
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
            Text("Vehicle Finder")
        }
    }
}
