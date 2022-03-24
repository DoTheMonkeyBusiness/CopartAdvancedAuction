package com.cprt.advancedauction.logIn.foundation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cprt.advancedauction.core.ScreenSize
import com.cprt.advancedauction.core.tools.LocalScreenSize
import com.cprt.advancedauction.foundation.AATopBar

@Composable
internal fun CommonWindow(
    modifier: Modifier = Modifier,
    isNeedToShowProgress: Boolean = false,
    navigationIcon: @Composable (() -> Unit)? = null,
    additionalContent: @Composable () -> Unit = {},
    cardContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        AATopBar(
            navigationIcon = navigationIcon,
        )
        if (LocalScreenSize.current == ScreenSize.Large) {
            CommonWindowLarge(
                modifier = modifier,
                additionalContent = additionalContent,
                cardContent = cardContent
            )
        } else {
            CommonWindowMedium(
                modifier = modifier,
                additionalContent = additionalContent,
                cardContent = cardContent
            )
        }
    }

    FullScreenProgress(
        isVisible = isNeedToShowProgress
    )
}
