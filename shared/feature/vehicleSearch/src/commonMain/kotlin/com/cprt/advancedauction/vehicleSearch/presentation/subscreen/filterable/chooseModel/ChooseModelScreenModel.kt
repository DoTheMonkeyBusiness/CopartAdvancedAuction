package com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.chooseModel

import com.cprt.advancedauction.core.useCase.ResultOf
import com.cprt.advancedauction.vehicleSearch.domain.useCase.FilterListUseCase
import com.cprt.advancedauction.vehicleSearch.domain.useCase.GetModelsUseCase
import com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.FilterListScreenModel

internal class ChooseModelScreenModel(
    filterListUseCase: FilterListUseCase,
    private val getModelsUseCase: GetModelsUseCase,
) : FilterListScreenModel(filterListUseCase) {

    override suspend fun getResultState(selectedManufacturer: String): State {
        return when (val result = getModelsUseCase(selectedManufacturer)) {
            is ResultOf.Success -> {
                val value = result.value

                State.Success(
                    values = value,
                    selected = value.firstOrNull() ?: "Any"
                )
            }
            is ResultOf.Failure -> State.Error
        }
    }
}
