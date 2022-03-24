package com.cprt.advancedauction.internalNotification.koin

import com.cprt.advancedauction.core.internalNotification.InternalNotificationManager
import com.cprt.advancedauction.internalNotification.presentation.NotificationManagerViewModel
import com.cprt.advancedauction.internalNotification.presentation.NotificationViewModel
import org.koin.dsl.binds
import org.koin.dsl.module

val internalNotificationModule = module {
    single {
        NotificationManagerViewModel()
    }.binds(
        arrayOf(
            InternalNotificationManager::class,
            NotificationViewModel::class
        )
    )
}
