package com.cprt.advancedauction.firebaseauth.data.service

import com.cprt.advancedauction.FirebaseNetworkConfig
import com.cprt.advancedauction.firebaseauth.data.requestBodyEntity.CredentialsRequestBody
import com.cprt.advancedauction.firebaseauth.data.responseEntity.TokenInfoEntity
import com.cprt.advancedauction.firebaseauth.util.Constants
import com.cprt.advancedauction.firebaseauth.util.Constants.Version.V1
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class SignUpService(
    private val client: HttpClient,
) : AuthService.SignUp {

    override suspend fun load(param: CredentialsRequestBody): TokenInfoEntity = client.post("$V1/accounts:signUp") {
        url.protocol = URLProtocol.HTTPS
        host = Constants.Url.IDENTITYTOOLKIT_HOST_URL
        contentType(ContentType.Application.Json)
        parameter(Constants.API_KEY, FirebaseNetworkConfig.FIREBASE_API_KEY)
        setBody(param)
    }.body()
}