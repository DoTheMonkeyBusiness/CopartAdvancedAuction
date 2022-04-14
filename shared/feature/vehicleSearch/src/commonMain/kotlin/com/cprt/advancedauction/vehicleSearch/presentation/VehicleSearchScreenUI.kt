package com.cprt.advancedauction.vehicleSearch.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screenModel.getScreenModel
import com.cprt.advancedauction.foundation.AADivider
import com.cprt.advancedauction.foundation.AATopBar
import com.cprt.advancedauction.foundation.button.AABackButton
import com.cprt.advancedauction.foundation.button.AAMainButton
import com.cprt.advancedauction.navigation.tools.VehicleSearchScreen
import com.cprt.advancedauction.recources.icons.AAIcons
import com.cprt.advancedauction.recources.icons.aaicons.IcChevronRight

private const val FILTER_RIGHT_SECTION_ALPHA = 0.75f

class VehicleSearchScreenUI : VehicleSearchScreen() {

    @Composable
    override fun MainPaneContent() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<VehicleSearchScreenModel>()

        Filters(
            screenModel = screenModel,
            navigator = navigator,
        )
    }

    @Composable
    override fun TobBarContent() {
        val navigator = LocalNavigator.currentOrThrow

        AATopBar(
            navigationIcon = {
                AABackButton(
                    onClick = {
                        navigator.popUntilRoot()
                    }
                )
            },
            title = "Vehicle search",
        )
    }

    @Composable
    private fun Filters(
        modifier: Modifier = Modifier,
        screenModel: VehicleSearchScreenModel,
        navigator: Navigator,
    ) {
        val state: VehicleSearchScreenModel.State by screenModel.stateFlow.collectAsState()
        val filterItems = state.filters
        val selectedItem = state.selectedItem

        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            itemsIndexed(filterItems) { index, filterItem ->
                val isSelected = index == selectedItem
                val isEnabled = remember(filterItems) { screenModel.isFilterEnabled(filterItem) }

                SelectionSell(
                    label = filterItem.header,
                    value = filterItem.textValue,
                    isEnabled = isEnabled,
                    isSelected = isSelected,
                    onClick = {
                        screenModel.itemSelected(index)
                        navigator.changePane(
                            screenModel.getScreenToNavigate(
                                filterOption = filterItem,
                            )
                        )
                    },
                )
                AADivider()
            }
            item {
                AAMainButton(
                    modifier = Modifier.padding(16.dp),
                    text = "Submit",
                    onClick = {},
                )
            }
        }
    }

    @Composable
    private fun SelectionSell(
        modifier: Modifier = Modifier,
        label: String,
        value: String,
        isEnabled: Boolean = true,
        isSelected: Boolean = false,
        onClick: () -> Unit,
    ) {
        Row(
            modifier = modifier
                .alpha(if (isEnabled) 1.0f else 0.5f)
                .fillMaxWidth()
                .selectable(
                    enabled = isEnabled,
                    selected = isSelected,
                    onClick = onClick
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier.alpha(FILTER_RIGHT_SECTION_ALPHA),
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Icon(
                modifier = Modifier.alpha(if (isSelected && isInTwoPaneMode) FILTER_RIGHT_SECTION_ALPHA else 0f),
                imageVector = AAIcons.IcChevronRight,
                contentDescription = null,
            )
        }
    }
}
