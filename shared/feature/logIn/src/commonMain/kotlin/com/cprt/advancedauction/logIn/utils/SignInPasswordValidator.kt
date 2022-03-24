package com.cprt.advancedauction.logIn.utils

import com.cprt.advancedauction.core.exception.ValidationException
import com.cprt.advancedauction.core.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.utils.Validator

internal class SignInPasswordValidator(
    private val loginErrorString: LoginErrorString,
) : Validator<String> {

    override fun validate(source: String) {

        when {
            source.isBlank() -> throw ValidationException(loginErrorString.emptyPasswordField)
        }
    }
}
