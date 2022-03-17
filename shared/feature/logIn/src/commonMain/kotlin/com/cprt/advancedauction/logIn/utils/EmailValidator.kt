package com.cprt.advancedauction.logIn.utils

import com.cprt.advancedauction.core.screen.exception.ValidationException
import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.screen.utils.Validator

internal class EmailValidator(
    private val loginErrorString: LoginErrorString,
) : Validator<String> {

    override fun validate(source: String) {
        val regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$".toRegex()

        when {
            source.isBlank() -> throw ValidationException(loginErrorString.emptyEmailField)
            !(regexPattern matches source) -> throw ValidationException(loginErrorString.emailIsNotValid)
        }
    }
}
