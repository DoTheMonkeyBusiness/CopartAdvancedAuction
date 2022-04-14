package com.cprt.advancedauction.vehicleSearch.presentation.subscreen.chooseYear

import com.cprt.advancedauction.core.internalNotification.InternalNotificationManager
import com.cprt.advancedauction.core.screenModel.AAScreenModel
import com.cprt.advancedauction.dateTime.DateTime

internal class ChooseYearScreenModel(
    dateTime: DateTime,
    private val internalNotificationManager: InternalNotificationManager,
) : AAScreenModel<ChooseYearScreenModel.State>(State.Initial) {

    val sliderBoundaries: IntRange = (dateTime.yearCurrent - 100)..(dateTime.yearCurrent + 1)

    fun onInit(yearRange: IntRange) {
        if (state != State.Initial) return

        state = State.YearRange(
            start = yearRange.first,
            end = yearRange.last,
        )
    }

    fun onStartYearChanged(start: Int) {
        when (val currentState = state) {
            is State.YearRange -> state = currentState.copy(start = start)
        }
    }

    fun onEndYearChanged(end: Int) {
        when (val currentState = state) {
            is State.YearRange -> state = currentState.copy(end = end)
        }
    }

    fun onSubmit() {
        when (val currentState = state) {
            is State.YearRange -> processSubmit(currentState)
        }
    }

    fun onSubmitted(range: IntRange) {
        state = State.YearRange(
            start = range.first,
            end = range.last
        )
    }

    private fun processSubmit(
        currentState: State.YearRange,
    ) {
        val start = currentState.start
        val end = currentState.end

        if (start > end) {
            internalNotificationManager.show("The start year must be less than or equal to the end year!!")
            return
        }

        state = State.Submit(
            range = start..end,
        )
    }

    sealed class State {
        object Initial : State()

        data class YearRange(
            val start: Int,
            val end: Int,
        ) : State()

        data class Submit(
            val range: IntRange,
        ) : State()
    }
}
