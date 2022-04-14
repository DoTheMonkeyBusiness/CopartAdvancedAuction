package com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.vehicleSearch.foundation.FilterableList

@Composable
internal fun FilterableListScreenUI(
    screenModel: FilterListScreenModel,
    selectedManufacturer: String,
    onValueChosen: (String) -> Unit,
    navigator: Navigator,
    isInTwoPaneMode: Boolean,
) {
    val state: FilterListScreenModel.State by screenModel.stateFlow.collectAsState()

    when (val currentState = state) {
        is FilterListScreenModel.State.Init -> screenModel.onInit(selectedManufacturer)
        is FilterListScreenModel.State.Loading -> Unit
        is FilterListScreenModel.State.Error -> Unit
        is FilterListScreenModel.State.Success -> FilterableList(
            elements = currentState.values,
            selectedElement = currentState.selected,
            onElementSelected = {
                onValueChosen(it)
                screenModel.onManufacturerSelected(it)
                if (!isInTwoPaneMode) {
                    navigator.pop()
                }
            },
            filterText = screenModel.filterText,
            onTextValueChange = screenModel::onFilter,
        )
    }
}
