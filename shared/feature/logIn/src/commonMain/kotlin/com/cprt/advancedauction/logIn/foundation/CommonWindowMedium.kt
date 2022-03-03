package com.cprt.advancedauction.logIn.foundation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.foundation.button.AABackButton
import com.cprt.advancedauction.foundation.spacer.HSpacer
import com.cprt.advancedauction.theme.icons.AAIcons
import com.cprt.advancedauction.theme.icons.aaicons.IcCopartLogo

@Composable
internal fun CommonWindowMedium(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    additionalContent: @Composable () -> Unit,
    cardContent: @Composable () -> Unit,
) {
    if (navigator.canPop) {
        AABackButton(
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp
            ),
            onClick = {
                navigator.pop()
            }
        )
    }
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