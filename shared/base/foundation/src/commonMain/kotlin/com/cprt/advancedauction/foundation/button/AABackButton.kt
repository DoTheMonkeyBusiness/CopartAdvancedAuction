package com.cprt.advancedauction.foundation.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.cprt.advancedauction.theme.icons.AAIcons
import com.cprt.advancedauction.theme.icons.aaicons.OutlineArrowBack

@Composable
fun AABackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
    ) {
        Icon(
            contentDescription = null,
            imageVector = AAIcons.OutlineArrowBack,
        )
    }
}