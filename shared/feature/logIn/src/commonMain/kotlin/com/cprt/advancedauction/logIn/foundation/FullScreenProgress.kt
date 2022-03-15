package com.cprt.advancedauction.logIn.foundation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
internal fun FullScreenProgress(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
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
}
