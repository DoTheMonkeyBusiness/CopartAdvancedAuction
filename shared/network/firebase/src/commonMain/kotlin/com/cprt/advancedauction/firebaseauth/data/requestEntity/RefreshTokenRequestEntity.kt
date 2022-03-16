package com.cprt.advancedauction.firebaseauth.data.requestEntity

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class RefreshTokenRequestEntity(
    @SerialName("refresh_token")
    val refreshToken: String,
    @EncodeDefault
    @SerialName("grant_type")
    val grantType: String = "refresh_token",
)
