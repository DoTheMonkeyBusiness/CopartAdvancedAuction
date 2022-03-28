package com.cprt.advancedauction.vehicleFinder.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.core.screen.Screen

internal data class ActionModel(
    val name: String,
    val icon: ImageVector,
    val screenToGo: Screen,
)
