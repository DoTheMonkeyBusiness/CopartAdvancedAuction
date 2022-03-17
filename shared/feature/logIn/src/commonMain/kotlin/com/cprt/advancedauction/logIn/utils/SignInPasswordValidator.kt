package com.cprt.advancedauction.logIn.utils

import com.cprt.advancedauction.core.screen.exception.ValidationException
import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.screen.utils.Validator

internal class SignInPasswordValidator(
    private val loginErrorString: LoginErrorString,
) : Validator<String> {

    override fun validate(source: String) {

        when {
            source.isBlank() -> throw ValidationException(loginErrorString.emptyPasswordField)
        }
    }
}
