package com.cprt.advancedauction.navigation.tab

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.cprt.advancedauction.foundation.bottomNavigation.AABottomNavigationItem

@Composable
fun RowScope.TabNavigationItem(
    tab: NavigationTab,
    tabNavigator: TabNavigator,
) {
    val isSelected = tabNavigator.current.key == tab.key
    val icon = tab.getOptions(isSelected).icon ?: throw RuntimeException("Icon must be provided")
    val title = tab.options.title

    AABottomNavigationItem(
        selected = isSelected,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = icon, contentDescription = title) }
    )
}
