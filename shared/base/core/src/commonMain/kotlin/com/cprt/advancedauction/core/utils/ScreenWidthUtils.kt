package com.cprt.advancedauction.core.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cprt.advancedauction.core.ScreenSize

fun calculateScreenSize(screenWidth: Dp) = when (screenWidth) {
    in 0.dp..339.dp -> ScreenSize.Small
    in 340.dp..599.dp -> ScreenSize.Medium
    else -> ScreenSize.Large
}
