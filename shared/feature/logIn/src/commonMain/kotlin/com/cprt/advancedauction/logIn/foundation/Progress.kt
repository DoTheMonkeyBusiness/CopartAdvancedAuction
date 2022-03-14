package com.cprt.advancedauction.logIn.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.cprt.advancedauction.foundation.progress.AACircularProgressIndicator

@Composable
internal fun Progress(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
            )
            .pointerInput(Unit) {},
        contentAlignment = Alignment.Center,
    ) {
        AACircularProgressIndicator(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
