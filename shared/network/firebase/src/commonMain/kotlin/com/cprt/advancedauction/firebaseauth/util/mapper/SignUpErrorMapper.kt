package com.cprt.advancedauction.firebaseauth.util.mapper

import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.screen.utils.Mapper
import com.cprt.advancedauction.firebaseauth.data.responseEntity.SignUpErrorCode

internal class SignUpErrorMapper(
    private val loginErrorString: LoginErrorString
) : Mapper<SignUpErrorCode?, String> {

    override fun map(from: SignUpErrorCode?): String {
        return when (from) {
            SignUpErrorCode.EMAIL_EXISTS -> loginErrorString.emailExists
            SignUpErrorCode.INVALID_EMAIL -> loginErrorString.invalidEmail
            SignUpErrorCode.TOO_MANY_ATTEMPTS_TRY_LATER -> loginErrorString.tooManyAttempts
            else -> loginErrorString.unspecified
        }
    }
}
