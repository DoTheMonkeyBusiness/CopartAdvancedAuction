package com.cprt.advancedauction.auth.domain.model

data class TokenInfoModel(
    val accessToken: String,
    val refreshToken: String,
    val expirationDate: Long,
)
