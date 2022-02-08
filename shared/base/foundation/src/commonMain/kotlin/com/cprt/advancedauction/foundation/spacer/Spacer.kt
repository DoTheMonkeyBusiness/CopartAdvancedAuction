package com.cprt.advancedauction.foundation.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.Modifier

@Composable
fun HSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun VSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}
