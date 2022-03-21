package com.cprt.advancedauction.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.cprt.advancedauction.accountSettings.presentation.AccountSettingsTab
import com.cprt.advancedauction.core.screen.tools.MainScreen
import com.cprt.advancedauction.favouriteItems.presentation.FavouriteItemsTab
import com.cprt.advancedauction.foundation.bottomNavigation.AABottomNavigation
import com.cprt.advancedauction.navigation.tab.TabNavigationItem
import com.cprt.advancedauction.vehicleFinder.presentation.VehicleFinderTab

class MainScreenUI : MainScreen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(VehicleFinderTab) { tabNavigator ->
            Surface {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CurrentTab()
                    AABottomNavigation {
                        TabNavigationItem(
                            tab = VehicleFinderTab,
                            tabNavigator = tabNavigator
                        )
                        TabNavigationItem(
                            tab = FavouriteItemsTab,
                            tabNavigator = tabNavigator
                        )
                        TabNavigationItem(
                            tab = AccountSettingsTab,
                            tabNavigator = tabNavigator
                        )
                    }
                }
            }
        }
    }
}
