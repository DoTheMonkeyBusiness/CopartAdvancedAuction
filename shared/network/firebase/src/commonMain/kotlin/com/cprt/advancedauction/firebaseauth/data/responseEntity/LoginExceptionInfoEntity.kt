package com.cprt.advancedauction.firebaseauth.data.responseEntity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginErrorEntity<ErrorCode>(
    val error: LoginExceptionInfoEntity<ErrorCode>?,
)

@Serializable
data class LoginExceptionInfoEntity<ErrorCode>(
    val code: Int?,
    @SerialName("message") val errorCode: ErrorCode?
)
