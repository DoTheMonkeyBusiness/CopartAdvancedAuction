package com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.voyager.core.model.coroutineScope
import com.cprt.advancedauction.core.screenModel.AAScreenModel
import com.cprt.advancedauction.core.useCase.ResultOf
import com.cprt.advancedauction.vehicleSearch.domain.useCase.FilterListUseCase
import kotlinx.coroutines.launch

internal abstract class FilterListScreenModel(
    private val filterListUseCase: FilterListUseCase,
) : AAScreenModel<FilterListScreenModel.State>(State.Init) {

    private val fullList = mutableListOf<String>()

    var filterText by mutableStateOf(TextFieldValue())
        private set

    fun onInit(selectedManufacturer: String) {
        if (state != State.Init) return

        state = State.Loading
        coroutineScope.launch {
            val currentState = getResultState(selectedManufacturer)

            if (currentState is State.Success) {
                fullList.let {
                    it.clear()
                    it.addAll(currentState.values)
                }
            }

            state = currentState
        }
    }

    fun onFilter(textFieldValue: TextFieldValue) {
        filterText = textFieldValue

        val currentState = state

        if (currentState is State.Success) {
            processFilter(
                text = textFieldValue.text,
            )
        }
    }

    fun onManufacturerSelected(value: String) {
        val currentState = state
        if (currentState is State.Success) {
            state = currentState.copy(
                selected = value,
            )
        }
    }

    private fun processFilter(
        text: String,
    ) {
        coroutineScope.launch {
            val result = filterListUseCase(
                text to fullList,
            )

            when (result) {
                is ResultOf.Success -> {
                    val currentState = state

                    if (currentState is State.Success) {
                        state = currentState.copy(
                            values = result.value
                        )
                    }
                }
                is ResultOf.Failure -> throw Exception("Current implementation doesn't support failure.")
            }
        }
    }

    abstract suspend fun getResultState(selectedManufacturer: String): State

    sealed class State {
        object Init : State()
        object Loading : State()

        data class Success(
            val values: List<String>,
            val selected: String,
        ) : State()

        object Error : State()
    }
}
