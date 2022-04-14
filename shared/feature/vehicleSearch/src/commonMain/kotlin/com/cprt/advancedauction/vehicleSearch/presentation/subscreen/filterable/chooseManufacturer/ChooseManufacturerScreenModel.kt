package com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.chooseManufacturer

import com.cprt.advancedauction.core.useCase.ResultOf
import com.cprt.advancedauction.vehicleSearch.domain.useCase.FilterListUseCase
import com.cprt.advancedauction.vehicleSearch.domain.useCase.GetManufacturersUseCase
import com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.FilterListScreenModel

internal class ChooseManufacturerScreenModel(
    filterListUseCase: FilterListUseCase,
    private val getManufacturersUseCase: GetManufacturersUseCase,
) : FilterListScreenModel(filterListUseCase) {

    override suspend fun getResultState(selectedManufacturer: String): State {
        return when (val result = getManufacturersUseCase(Unit)) {
            is ResultOf.Success -> {
                State.Success(
                    values = result.value,
                    selected = selectedManufacturer,
                )
            }
            is ResultOf.Failure -> State.Error
        }
    }
}
