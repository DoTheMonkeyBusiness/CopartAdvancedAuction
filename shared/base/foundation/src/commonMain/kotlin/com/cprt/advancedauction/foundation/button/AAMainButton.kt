package com.cprt.advancedauction.foundation.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AAMainButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    AAMainButtonCommon(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = text,
        )
    }
}

@Composable
fun AAMainButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
        content = content,
    )
}

@Composable
private fun AAMainButtonCommon(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        content = content,
    )
}