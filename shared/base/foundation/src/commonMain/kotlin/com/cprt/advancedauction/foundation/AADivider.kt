package com.cprt.advancedauction.foundation

import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AADivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = thickness,
        startIndent = startIndent,
    )
}
