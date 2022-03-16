package com.cprt.advancedauction.firebaseauth.data.responseEntity

import kotlinx.serialization.Serializable

@Serializable
data class TokenInfoResponseEntity(
    val idToken: String?,
    val refreshToken: String?,
    val expiresIn: String?,
)
