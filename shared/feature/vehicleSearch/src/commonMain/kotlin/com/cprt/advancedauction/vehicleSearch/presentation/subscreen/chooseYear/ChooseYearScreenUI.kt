package com.cprt.advancedauction.vehicleSearch.presentation.subscreen.chooseYear

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screenModel.getScreenModel
import com.cprt.advancedauction.foundation.AATopBar
import com.cprt.advancedauction.foundation.button.AABackButton
import com.cprt.advancedauction.foundation.button.AAMainButton
import com.cprt.advancedauction.foundation.picker.NumberPicker
import com.cprt.advancedauction.navigation.screen.TwoPaneSubScreen

internal class ChooseYearScreenUI(
    private val yearRange: IntRange,
    private val onYearChosen: (IntRange) -> Unit,
) : TwoPaneSubScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val isInTwoPaneMode = isInTwoPaneMode
        val screenModel = getScreenModel<ChooseYearScreenModel>()
        val state: ChooseYearScreenModel.State by screenModel.stateFlow.collectAsState()
        var startYear by remember { mutableStateOf(yearRange.first) }
        var endYear by remember { mutableStateOf(yearRange.last) }

        when (val currentState = state) {
            is ChooseYearScreenModel.State.Initial -> screenModel.onInit(yearRange)
            is ChooseYearScreenModel.State.YearRange -> {
                startYear = currentState.start
                endYear = currentState.end
            }
            is ChooseYearScreenModel.State.Submit -> {
                onYearChosen(currentState.range)
                if (!isInTwoPaneMode) {
                    navigator.pop()
                } else {
                    screenModel.onSubmitted(currentState.range)
                }
            }
        }

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
                    title = "Year Range",
                )
            }
            YearRangePicker(
                startYear = startYear,
                endYear = endYear,
                boundaries = screenModel.sliderBoundaries,
                onStartChanged = screenModel::onStartYearChanged,
                onEndChanged = screenModel::onEndYearChanged,
                onSubmit = screenModel::onSubmit,
            )
        }
    }

    @Composable
    private fun ColumnScope.YearRangePicker(
        startYear: Int,
        endYear: Int,
        boundaries: IntRange,
        onStartChanged: (Int) -> Unit,
        onEndChanged: (Int) -> Unit,
        onSubmit: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(400.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "From:",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                NumberPicker(
                    value = startYear,
                    range = boundaries,
                    onValueChange = onStartChanged,
                )
                Text(
                    text = "To:",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                NumberPicker(
                    value = endYear,
                    range = boundaries,
                    onValueChange = onEndChanged,
                )
            }
            AAMainButton(
                text = "Submit",
                onClick = onSubmit
            )
        }
    }
}
