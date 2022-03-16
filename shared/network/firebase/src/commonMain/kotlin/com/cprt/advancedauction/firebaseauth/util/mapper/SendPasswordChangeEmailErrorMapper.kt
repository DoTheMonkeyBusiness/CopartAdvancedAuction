package com.cprt.advancedauction.firebaseauth.util.mapper

import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.screen.utils.Mapper
import com.cprt.advancedauction.firebaseauth.data.responseEntity.SendPasswordErrorCode

internal class SendPasswordChangeEmailErrorMapper(
    private val loginErrorString: LoginErrorString
) : Mapper<SendPasswordErrorCode?, String> {

    override fun map(from: SendPasswordErrorCode?): String {
        return when (from) {
            SendPasswordErrorCode.EMAIL_NOT_FOUND -> loginErrorString.emailNotFound
            SendPasswordErrorCode.INVALID_EMAIL -> loginErrorString.invalidEmail
            else -> loginErrorString.unspecified
        }
    }
}
