package com.cprt.advancedauction.firebaseauth.data.responseEntity

import kotlinx.serialization.Serializable

@Serializable
data class TokenInfoEntity(
    val idToken: String,
    val refreshToken: String,
    val expiresIn: String,
)
