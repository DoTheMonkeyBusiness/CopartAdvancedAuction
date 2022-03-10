package com.cprt.advancedauction.firebaseauth.data.responseEntity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenInfoEntity(
    @SerialName("id_token") val idToken: String?,
    @SerialName("refresh_token") val refreshToken: String?,
    @SerialName("expires_in") val expiresIn: String?,
)
