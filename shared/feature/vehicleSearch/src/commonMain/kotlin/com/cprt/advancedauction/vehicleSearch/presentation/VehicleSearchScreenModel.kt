package com.cprt.advancedauction.vehicleSearch.presentation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.core.screenModel.AAScreenModel
import com.cprt.advancedauction.dateTime.DateTime
import com.cprt.advancedauction.vehicleSearch.domain.model.FilterOption
import com.cprt.advancedauction.vehicleSearch.presentation.subscreen.chooseYear.ChooseYearScreenUI
import com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.chooseManufacturer.ChooseManufacturerScreenUI
import com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.chooseModel.ChooseModelScreenUI

private const val MANUFACTURER_INDEX = 0
private const val MODEL_INDEX = 1
private const val YEAR_RANGE_INDEX = 2
private const val NOT_SELECTED_ITEM_INDEX = -1

internal class VehicleSearchScreenModel(
    dateTime: DateTime,
) : AAScreenModel<VehicleSearchScreenModel.State>(
    State(
        filters = listOf(
            FilterOption.Manufacturer(
                value = "Any"
            ),
            FilterOption.Model(
                value = "Any"
            ),
            FilterOption.YearRange(
                range = dateTime.yearCurrent - 10..dateTime.yearCurrent
            ),
        ),
        selectedItem = NOT_SELECTED_ITEM_INDEX,
    )
) {

    private val selectedManufacturer: FilterOption.Manufacturer
        get() {
            return state
                .filters
                .first { it is FilterOption.Manufacturer } as FilterOption.Manufacturer
        }

    fun getScreenToNavigate(
        filterOption: FilterOption,
    ): Screen {
        return when (filterOption) {
            is FilterOption.Manufacturer -> ChooseManufacturerScreenUI(
                selectedManufacturer = filterOption.value,
                onManufacturerChosen = ::onManufacturerChosen
            )
            is FilterOption.Model -> ChooseModelScreenUI(
                selectedManufacturer = selectedManufacturer.value,
                onModelChosen = ::onModelChosen,
            )
            is FilterOption.YearRange -> ChooseYearScreenUI(
                yearRange = filterOption.range,
                onYearChosen = ::onYearChosen
            )
        }
    }

    fun isFilterEnabled(filter: FilterOption): Boolean {
        return when (filter) {
            is FilterOption.Manufacturer -> true
            is FilterOption.Model -> selectedManufacturer.value != "Any"
            is FilterOption.YearRange -> true
        }
    }

    fun itemSelected(
        index: Int
    ) {
        state = state.copy(
            selectedItem = index
        )
    }

    fun onSubmit(navigator: Navigator) {

    }

    private fun onManufacturerChosen(
        value: String,
    ) {
        val list = state.filters.toMutableList().apply {
            set(
                MANUFACTURER_INDEX,
                FilterOption.Manufacturer(
                    value = value
                )
            )
            set(
                MODEL_INDEX,
                FilterOption.Model(
                    value = "Any"
                )
            )
        }

        state = state.copy(
            filters = list
        )
    }

    private fun onModelChosen(
        value: String,
    ) {
        val list = state.filters.toMutableList().apply {
            set(
                MODEL_INDEX,
                FilterOption.Model(
                    value = value
                )
            )
        }

        state = state.copy(
            filters = list
        )
    }

    private fun onYearChosen(
        range: IntRange,
    ) {
        val list = state.filters.toMutableList().apply {
            set(
                YEAR_RANGE_INDEX,
                FilterOption.YearRange(
                    range = range
                )
            )
        }

        state = state.copy(
            filters = list
        )
    }

    data class State(
        val filters: List<FilterOption>,
        val selectedItem: Int,
    )
}
