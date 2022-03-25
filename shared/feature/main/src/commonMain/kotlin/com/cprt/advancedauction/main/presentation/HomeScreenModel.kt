package com.cprt.advancedauction.main.presentation

import cafe.adriel.voyager.core.model.coroutineScope
import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.auth.util.isLoggedIn
import com.cprt.advancedauction.core.screenModel.AAScreenModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

internal class HomeScreenModel(
    private val authTokenHolder: AuthTokenHolder
) : AAScreenModel<HomeScreenModel.State>(
    State(
        isUserLoggedIn = authTokenHolder.userLoginState.value.isLoggedIn
    )
) {

    init {
        coroutineScope.launch {
            observeLoginState()
        }
    }

    private suspend fun observeLoginState() {
        authTokenHolder.userLoginState.collect {
            state = state.copy(
                isUserLoggedIn = it.isLoggedIn
            )
        }
    }

    data class State(
        val isUserLoggedIn: Boolean
    )
}
