package com.cprt.advancedauction.core.screen.screenModel

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//todo: Delete after koin-compose gets multiplatform support
abstract class AAScreenModel<State>(initialState: State) : ScreenModel {
    private val _state = MutableStateFlow(initialState)
    val stateFlow: StateFlow<State> = _state

    var state: State
        get() = stateFlow.value
        protected set(value) {
            _state.value = value
        }
}
