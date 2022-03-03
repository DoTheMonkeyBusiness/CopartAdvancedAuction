package com.cprt.advancedauction.logIn.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.core.screen.ScreenSize
import com.cprt.advancedauction.core.screen.tools.LocalScreenSize
import com.cprt.advancedauction.foundation.progress.AACircularProgressIndicator

@Composable
internal fun CommonWindow(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    isNeedToShowProgress: Boolean = false,
    additionalContent: @Composable () -> Unit = {},
    cardContent: @Composable () -> Unit,
) {
    if (LocalScreenSize.current == ScreenSize.Large) {
        CommonWindowLarge(
            modifier = modifier,
            navigator = navigator,
            additionalContent = additionalContent,
            cardContent = cardContent
        )
    } else {
        CommonWindowMedium(
            modifier = modifier,
            navigator = navigator,
            additionalContent = additionalContent,
            cardContent = cardContent
        )
    }
    if (isNeedToShowProgress) {
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
