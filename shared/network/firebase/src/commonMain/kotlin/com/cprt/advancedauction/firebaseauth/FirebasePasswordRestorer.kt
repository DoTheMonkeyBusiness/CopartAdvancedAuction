package com.cprt.advancedauction.firebaseauth

import com.cprt.advancedauction.auth.PasswordRestorer
import com.cprt.advancedauction.firebaseauth.data.requestEntity.ResetEmailRequestEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.SendPasswordErrorCode
import com.cprt.advancedauction.firebaseauth.data.service.passwordReset.PasswordResetService
import com.cprt.advancedauction.firebaseauth.util.mapper.SendPasswordChangeEmailErrorMapper
import com.cprt.advancedauction.firebaseauth.util.processLoginException
import kotlinx.serialization.json.Json

internal class FirebasePasswordRestorer(
    private val sendResetCodeErrorMapper: SendPasswordChangeEmailErrorMapper,
    private val sendResetCodeService: PasswordResetService.SendResetEmail,
    private val json: Json,
) : PasswordRestorer {

    override suspend fun sendPasswordResetEmail(
        email: String
    ) {
        runCatching {
            sendResetCodeService.load(
                ResetEmailRequestEntity(
                    email = email,
                )
            )
        }.getOrElse {
            it.processLoginException<SendPasswordErrorCode>(
                json = json,
                mapper = sendResetCodeErrorMapper,
            )
        }
    }
}
