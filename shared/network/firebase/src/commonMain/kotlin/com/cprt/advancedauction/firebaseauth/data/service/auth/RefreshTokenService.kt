package com.cprt.advancedauction.firebaseauth.data.service.auth

import com.cprt.advancedauction.FirebaseNetworkConfig
import com.cprt.advancedauction.firebaseauth.data.requestEntity.RefreshTokenRequestEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.RefreshTokenResponseEntity
import com.cprt.advancedauction.firebaseauth.util.Constants
import com.cprt.advancedauction.firebaseauth.util.Constants.Url.SECURETOKEN_HOST_URL
import com.cprt.advancedauction.firebaseauth.util.Constants.Version.V1
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class RefreshTokenService(
    private val client: HttpClient,
) : AuthService.RefreshToken {

    override suspend fun load(param: RefreshTokenRequestEntity): RefreshTokenResponseEntity = client.post("$V1/token") {
        url.protocol = URLProtocol.HTTPS
        host = SECURETOKEN_HOST_URL
        parameter(Constants.API_KEY, FirebaseNetworkConfig.FIREBASE_API_KEY)
        contentType(ContentType.Application.Json)
        setBody(param)
    }.body()
}
