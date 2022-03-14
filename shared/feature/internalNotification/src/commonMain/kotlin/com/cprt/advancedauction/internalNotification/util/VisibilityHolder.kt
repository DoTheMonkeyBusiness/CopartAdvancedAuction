package com.cprt.advancedauction.internalNotification.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val NOTIFICATION_VISIBILITY_TIME_MILLISECONDS = 3000L

internal class VisibilityHolder(
    private val coroutineScope: CoroutineScope
) {

    var isVisible by mutableStateOf(false)
        private set

    fun updateVisibility() {
        isVisible = true

        coroutineScope.launch {
            delay(NOTIFICATION_VISIBILITY_TIME_MILLISECONDS)
            isVisible = false
        }
    }
}
