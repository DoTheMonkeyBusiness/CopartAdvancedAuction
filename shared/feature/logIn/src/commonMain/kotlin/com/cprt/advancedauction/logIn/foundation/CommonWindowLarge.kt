package com.cprt.advancedauction.logIn.foundation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.foundation.AACard
import com.cprt.advancedauction.foundation.button.AABackButton
import com.cprt.advancedauction.foundation.spacer.HSpacer
import com.cprt.advancedauction.theme.icons.AAIcons
import com.cprt.advancedauction.theme.icons.aaicons.IcCopartLogo

@Composable
internal fun CommonWindowLarge(
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
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(48.dp),
            contentDescription = null,
            imageVector = AAIcons.IcCopartLogo,
        )
        HSpacer(16.dp)
        AACard(
            modifier = Modifier.width(360.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                cardContent()
            }
        }
        HSpacer(16.dp)
        Box(
            modifier = Modifier.width(360.dp),
            contentAlignment = Alignment.TopCenter,
        ) {
            additionalContent()
        }
    }
}