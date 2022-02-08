package com.cprt.advancedauction.logIn.foundation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cprt.advancedauction.core.screen.ScreenSize
import com.cprt.advancedauction.core.screen.tools.LocalScreenSize

@Composable
internal fun CommonWindow(
    modifier: Modifier = Modifier,
    additionalContent: @Composable () -> Unit = {},
    cardContent: @Composable () -> Unit,
) {
    if (LocalScreenSize.current == ScreenSize.Large) {
        CommonWindowLarge(
            modifier,
            additionalContent,
            cardContent
        )
    } else {
        CommonWindowMedium(
            modifier,
            additionalContent,
            cardContent
        )
    }
}
