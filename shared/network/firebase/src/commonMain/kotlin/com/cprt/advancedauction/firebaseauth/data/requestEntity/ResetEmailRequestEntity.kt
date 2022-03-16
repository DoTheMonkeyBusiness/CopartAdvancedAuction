package com.cprt.advancedauction.firebaseauth.data.requestEntity

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class ResetEmailRequestEntity(
    val email: String,
    @EncodeDefault
    val requestType: String = "PASSWORD_RESET"
)
