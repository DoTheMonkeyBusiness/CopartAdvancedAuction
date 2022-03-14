package com.cprt.advancedauction.internalNotification.presentation

import androidx.compose.runtime.mutableStateListOf
import com.cprt.advancedauction.core.screen.internalNotification.InternalNotificationManager
import com.cprt.advancedauction.internalNotification.domain.model.NotificationModel
import com.cprt.advancedauction.internalNotification.util.VisibilityHolder
import kotlinx.coroutines.CoroutineScope

internal class NotificationManagerViewModel : InternalNotificationManager, NotificationViewModel {

    private var coroutineScope: CoroutineScope? = null

    private val _notifications = mutableStateListOf<Pair<NotificationModel, VisibilityHolder>>()

    override val notifications: List<Pair<NotificationModel, VisibilityHolder>> = _notifications

    override fun show(message: String) {
        if (message.isEmpty()) return

        coroutineScope?.let {
            _notifications.add(NotificationModel(message) to VisibilityHolder(it))
        }
    }

    override fun onBindUI(scope: CoroutineScope) {
        coroutineScope = scope
    }

    override fun onRealiseUI() {
        coroutineScope = null
    }

    override fun onNotificationRemoved() {
        if (_notifications.none { it.second.isVisible }) {
            _notifications.clear()
        }
    }
}
