package com.cprt.advancedauction.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.cprt.advancedauction.accountSettings.presentation.AccountSettingsTab
import com.cprt.advancedauction.core.screenModel.getScreenModel
import com.cprt.advancedauction.core.tools.MainScreen
import com.cprt.advancedauction.favouriteItems.presentation.FavouriteItemsTab
import com.cprt.advancedauction.foundation.bottomNavigation.AABottomNavigation
import com.cprt.advancedauction.navigation.tab.TabNavigationItem
import com.cprt.advancedauction.vehicleFinder.presentation.VehicleFinderTab

class MainScreenUI : MainScreen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(VehicleFinderTab) { tabNavigator ->
            val screenModel = getScreenModel<HomeScreenModel>()
            val state by screenModel.stateFlow.collectAsState()

            Surface {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CurrentTab()
                    BottomTabs(
                        tabNavigator = tabNavigator,
                        isLoggedIn = state.isUserLoggedIn
                    )
                }
            }
        }
    }

    @Composable
    private fun BoxScope.BottomTabs(
        modifier: Modifier = Modifier,
        tabNavigator: TabNavigator,
        isLoggedIn: Boolean,
    ) {
        val tabItems = remember(isLoggedIn) {
            buildList {
                add(VehicleFinderTab)
                if (isLoggedIn) {
                    add(FavouriteItemsTab)
                }
                add(AccountSettingsTab)
            }
        }

        AABottomNavigation(
            modifier = modifier,
        ) {
            tabItems.forEach {
                TabNavigationItem(
                    tab = it,
                    tabNavigator = tabNavigator
                )
            }
        }
    }
}
