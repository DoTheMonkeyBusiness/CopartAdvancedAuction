package com.cprt.advancedauction.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun AALifecycleEffect(
    key: Any? = Unit,
    onDestroy: () -> Unit = {},
    onLaunch: () -> Unit = {},
) {
    DisposableEffect(key) {
        onLaunch()
        onDispose(onDestroy)
    }
}
