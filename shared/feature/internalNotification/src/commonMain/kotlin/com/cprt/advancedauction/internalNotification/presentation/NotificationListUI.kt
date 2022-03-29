package com.cprt.advancedauction.internalNotification.presentation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cprt.advancedauction.core.koin.get
import com.cprt.advancedauction.foundation.spacer.HSpacer
import com.cprt.advancedauction.internalNotification.foundation.NotificationUI

@Composable
fun NotificationListUI(
    modifier: Modifier = Modifier,
) {
    val viewModel = get<NotificationViewModel>()
    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
        ) {
            viewModel.notifications.reversed().forEach {
                HSpacer(8.dp)
                NotificationUI(
                    notificationData = it,
                    screenWidth = this@BoxWithConstraints.maxWidth,
                    onDispose = viewModel::onNotificationRemoved
                )
            }
        }
    }

    DisposableEffect(coroutineScope) {
        viewModel.onBindUI(coroutineScope)

        onDispose {
            viewModel.onRealiseUI()
        }
    }
}
