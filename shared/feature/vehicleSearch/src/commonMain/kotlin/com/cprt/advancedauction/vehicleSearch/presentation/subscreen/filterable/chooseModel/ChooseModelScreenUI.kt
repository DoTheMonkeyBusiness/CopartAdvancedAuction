package com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.chooseModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screenModel.getScreenModel
import com.cprt.advancedauction.foundation.AATopBar
import com.cprt.advancedauction.foundation.button.AABackButton
import com.cprt.advancedauction.navigation.screen.TwoPaneSubScreen
import com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.FilterableListScreenUI

internal class ChooseModelScreenUI(
    private val selectedManufacturer: String,
    private val onModelChosen: (String) -> Unit
) : TwoPaneSubScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val isInTwoPaneMode = isInTwoPaneMode
        val screenModel = getScreenModel<ChooseModelScreenModel>()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (!isInTwoPaneMode) {
                AATopBar(
                    navigationIcon = {
                        AABackButton(
                            onClick = {
                                navigator.pop()
                            }
                        )
                    },
                    title = "Model",
                )
            }

            FilterableListScreenUI(
                screenModel = screenModel,
                selectedManufacturer = selectedManufacturer,
                onValueChosen = onModelChosen,
                navigator = navigator,
                isInTwoPaneMode = isInTwoPaneMode,
            )
        }
    }
}
