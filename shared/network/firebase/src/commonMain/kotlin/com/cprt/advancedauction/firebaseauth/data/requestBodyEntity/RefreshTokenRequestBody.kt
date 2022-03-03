package com.cprt.advancedauction.firebaseauth.data.requestBodyEntity

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class RefreshTokenRequestBody(
    @SerialName("refresh_token")
    val refreshToken: String,
    @EncodeDefault
    @SerialName("grant_type")
    val grantType: String = "refresh_token",
)
