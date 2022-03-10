package com.cprt.advancedauction.firebaseauth.util.mapper

import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.screen.utils.Mapper
import com.cprt.advancedauction.firebaseauth.data.responseEntity.SignInErrorCode

internal class SignInErrorMapper(
    private val loginErrorString: LoginErrorString
) : Mapper<SignInErrorCode?, String> {

    override fun map(from: SignInErrorCode?): String {
        return when (from) {
            SignInErrorCode.EMAIL_NOT_FOUND,
            SignInErrorCode.INVALID_EMAIL -> loginErrorString.emailNotFound
            SignInErrorCode.INVALID_PASSWORD -> loginErrorString.invalidPassword
            SignInErrorCode.USER_DISABLED -> loginErrorString.userDisabled
            else -> loginErrorString.unspecified
        }
    }
}
