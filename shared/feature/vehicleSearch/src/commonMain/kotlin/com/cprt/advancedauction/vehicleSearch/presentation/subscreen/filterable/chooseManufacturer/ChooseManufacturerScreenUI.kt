package com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.chooseManufacturer

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

internal class ChooseManufacturerScreenUI(
    private val selectedManufacturer: String,
    private val onManufacturerChosen: (String) -> Unit,
) : TwoPaneSubScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val isInTwoPaneMode = isInTwoPaneMode
        val screenModel = getScreenModel<ChooseManufacturerScreenModel>()

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
                    title = "Manufacturers",
                )
            }

            FilterableListScreenUI(
                screenModel = screenModel,
                selectedManufacturer = selectedManufacturer,
                onValueChosen = onManufacturerChosen,
                navigator = navigator,
                isInTwoPaneMode = isInTwoPaneMode,
            )
        }
    }
}
