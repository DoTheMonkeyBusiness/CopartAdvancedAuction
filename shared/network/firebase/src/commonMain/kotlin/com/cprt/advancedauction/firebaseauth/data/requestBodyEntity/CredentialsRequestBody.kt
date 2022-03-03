package com.cprt.advancedauction.firebaseauth.data.requestBodyEntity

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class CredentialsRequestBody(
    val email: String = "",
    val password: String = "",
    @EncodeDefault
    val returnSecureToken: Boolean = true,
)
