package com.cprt.advancedauction.firebaseauth.data.responseEntity

import kotlinx.serialization.Serializable

@Serializable
data class ResetCodeResponseEntity(
    val email: String?,
)
