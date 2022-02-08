package com.cprt.advancedauction.logIn.foundation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cprt.advancedauction.foundation.spacer.HSpacer
import com.cprt.advancedauction.theme.icons.AAIcons
import com.cprt.advancedauction.theme.icons.aaicons.IcCopartLogo

@Composable
internal fun CommonWindowMedium(
    modifier: Modifier = Modifier,
    additionalContent: @Composable () -> Unit,
    cardContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(
                top = 24.dp,
                bottom = 16.dp,
                start = 24.dp,
                end = 24.dp
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            contentDescription = null,
            imageVector = AAIcons.IcCopartLogo,
        )
        HSpacer(16.dp)
        cardContent()
        HSpacer(16.dp)
        additionalContent()
    }
}