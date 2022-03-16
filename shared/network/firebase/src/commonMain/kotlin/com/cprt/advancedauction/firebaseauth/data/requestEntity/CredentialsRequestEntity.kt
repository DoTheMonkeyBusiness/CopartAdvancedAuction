package com.cprt.advancedauction.firebaseauth.data.requestEntity

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class CredentialsRequestEntity(
    val email: String = "",
    val password: String = "",
    @EncodeDefault
    val returnSecureToken: Boolean = true,
)
