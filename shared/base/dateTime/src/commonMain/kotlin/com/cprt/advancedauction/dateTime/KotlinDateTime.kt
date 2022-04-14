package com.cprt.advancedauction.dateTime

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class KotlinDateTime : DateTime {

    private val currentMoment: Instant
        get() = Clock.System.now()

    override val secondsCurrent: Long
        get() = currentMoment.epochSeconds

    override val yearCurrent: Int = getCurrentYear()

    private fun getCurrentYear(): Int {
        val datetimeInSystemZone = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

        return datetimeInSystemZone.year
    }
}
