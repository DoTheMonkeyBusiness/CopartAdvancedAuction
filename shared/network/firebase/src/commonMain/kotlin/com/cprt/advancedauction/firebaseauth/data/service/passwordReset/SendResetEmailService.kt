package com.cprt.advancedauction.firebaseauth.data.service.passwordReset

import com.cprt.advancedauction.FirebaseNetworkConfig
import com.cprt.advancedauction.firebaseauth.data.requestEntity.ResetEmailRequestEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.ResetCodeResponseEntity
import com.cprt.advancedauction.firebaseauth.util.Constants
import com.cprt.advancedauction.firebaseauth.util.Constants.Url.IDENTITYTOOLKIT_HOST_URL
import com.cprt.advancedauction.firebaseauth.util.Constants.Version.V1
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class SendResetEmailService(
    private val client: HttpClient,
) : PasswordResetService.SendResetEmail {

    override suspend fun load(param: ResetEmailRequestEntity): ResetCodeResponseEntity = client.post("$V1/accounts:sendOobCode") {
        url.protocol = URLProtocol.HTTPS
        host = IDENTITYTOOLKIT_HOST_URL
        parameter(Constants.API_KEY, FirebaseNetworkConfig.FIREBASE_API_KEY)
        contentType(ContentType.Application.Json)
        setBody(param)
    }.body()
}
