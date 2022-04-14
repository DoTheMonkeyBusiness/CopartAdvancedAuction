package com.cprt.advancedauction.favouriteItems.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cprt.advancedauction.navigation.tab.NavigationTab

object FavouriteItemsTab : NavigationTab() {

    override val screen: Screen
        get() = FavouriteItemsScreen()

    @Composable
    override fun getOptions(isSelected: Boolean): TabOptions {
        val vector = if (isSelected) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder
        val icon = rememberVectorPainter(vector)

        return remember(icon) {
            TabOptions(
                index = 1u,
                title = "Favourite",
                icon = icon
            )
        }
    }
}
