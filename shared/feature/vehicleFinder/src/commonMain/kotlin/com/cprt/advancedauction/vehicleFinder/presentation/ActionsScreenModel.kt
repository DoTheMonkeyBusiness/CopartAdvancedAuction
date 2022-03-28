package com.cprt.advancedauction.vehicleFinder.presentation

import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.core.screenModel.AAScreenModel
import com.cprt.advancedauction.core.useCase.ResultOf
import com.cprt.advancedauction.vehicleFinder.domain.model.ActionModel
import com.cprt.advancedauction.vehicleFinder.domain.useCase.GetActionsUseCase
import kotlinx.coroutines.launch

internal class ActionsScreenModel(
    private val getActionsUseCase: GetActionsUseCase,
) : AAScreenModel<ActionsScreenModel.State>(State.Initial) {

    fun onInit() {
        if (state != State.Initial) return
        state = State.Waiting
        coroutineScope.launch {
            when (val result = getActionsUseCase(Unit)) {
                is ResultOf.Success -> {
                    state = State.ActionsReceived(result.value)
                }
                is ResultOf.Failure -> throw Exception("Current implementation doesn't expect error state.")
            }
        }
    }

    fun openScreen(
        navigator: Navigator,
        screen: Screen,
    ) {
        navigator.push(screen)
    }

    sealed class State {
        object Initial : State()
        object Waiting : State()
        data class ActionsReceived(
            val actions: List<ActionModel>
        ) : State()
    }
}
