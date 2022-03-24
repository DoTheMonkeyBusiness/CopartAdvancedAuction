package com.cprt.advancedauction.foundation.bottomNavigation

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cprt.advancedauction.core.ScreenSize
import com.cprt.advancedauction.core.tools.LocalScreenSize

@Composable
fun BoxScope.AABottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable RowScope.() -> Unit
) {
    val contentModifier = if (LocalScreenSize.current == ScreenSize.Large) {
        modifier
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .width(400.dp)
    } else {
        modifier
    }

    BottomNavigation(
        modifier = contentModifier
            .align(Alignment.BottomCenter),
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        content = content,
    )
}
