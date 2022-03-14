package com.cprt.advancedauction.internalNotification.domain.model

import kotlin.random.Random

data class NotificationModel(
    val text: String,
) {
    val id = Random.nextInt()
}
