package com.cprt.advancedauction.firebaseauth.util

import com.cprt.advancedauction.core.screen.utils.Mapper
import com.cprt.advancedauction.firebaseauth.data.responseEntity.LoginErrorEntity
import com.cprt.advancedauction.firebaseauth.exception.LoginException
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal suspend inline fun <reified T> Throwable.processLoginException(
    json: Json,
    mapper: Mapper<T?, String>
): Nothing {
    if (this !is ClientRequestException) {
        throw this
    }

    val exceptionResponse = response
    val exceptionEntity = json.decodeFromString<LoginErrorEntity<T>>(
        exceptionResponse.bodyAsText()
    )
    val loginErrorMessage = mapper.map(exceptionEntity.error?.errorCode)

    throw LoginException(
        exceptionMessage = message,
        errorMessage = loginErrorMessage
    )
}
