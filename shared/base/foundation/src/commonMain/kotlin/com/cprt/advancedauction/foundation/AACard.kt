package com.cprt.advancedauction.foundation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun AACard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(30.dp),
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    border: BorderStroke? = null,
    elevation: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        shadowElevation = elevation,
        border = border,
        content = content
    )
}
