package com.cprt.advancedauction.logIn.foundation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
                vertical = 16.dp,
                horizontal = 24.dp,
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(48.dp),
            contentDescription = null,
            imageVector = AAIcons.IcCopartLogo,
        )
        HSpacer(16.dp)
        cardContent()
        HSpacer(16.dp)
        additionalContent()
    }
}