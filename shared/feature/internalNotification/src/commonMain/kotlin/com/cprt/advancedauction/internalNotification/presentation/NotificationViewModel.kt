package com.cprt.advancedauction.internalNotification.presentation

import com.cprt.advancedauction.internalNotification.domain.model.NotificationModel
import com.cprt.advancedauction.internalNotification.util.VisibilityHolder
import kotlinx.coroutines.CoroutineScope

internal interface NotificationViewModel {

    val notifications: List<Pair<NotificationModel, VisibilityHolder>>

    fun onBindUI(scope: CoroutineScope)

    fun onRealiseUI()

    fun onNotificationRemoved()
}
