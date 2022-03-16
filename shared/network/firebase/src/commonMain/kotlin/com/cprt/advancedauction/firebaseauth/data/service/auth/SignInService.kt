package com.cprt.advancedauction.firebaseauth.data.service.auth

import com.cprt.advancedauction.FirebaseNetworkConfig
import com.cprt.advancedauction.firebaseauth.data.requestEntity.CredentialsRequestEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.TokenInfoResponseEntity
import com.cprt.advancedauction.firebaseauth.util.Constants.API_KEY
import com.cprt.advancedauction.firebaseauth.util.Constants.Url.IDENTITYTOOLKIT_HOST_URL
import com.cprt.advancedauction.firebaseauth.util.Constants.Version.V1
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class SignInService(
    private val client: HttpClient,
) : AuthService.SignIn {

    override suspend fun load(param: CredentialsRequestEntity): TokenInfoResponseEntity = client.post("$V1/accounts:signInWithPassword") {
        url.protocol = URLProtocol.HTTPS
        host = IDENTITYTOOLKIT_HOST_URL
        contentType(ContentType.Application.Json)
        parameter(API_KEY, FirebaseNetworkConfig.FIREBASE_API_KEY)
        setBody(param)
    }.body()
}
