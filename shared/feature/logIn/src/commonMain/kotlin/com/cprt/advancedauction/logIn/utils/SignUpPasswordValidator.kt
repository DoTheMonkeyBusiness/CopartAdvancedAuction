package com.cprt.advancedauction.logIn.utils

import com.cprt.advancedauction.core.screen.exception.ValidationException
import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.screen.utils.Validator

private const val MINIMAL_PASSWORD_LENGTH = 6

class SignUpPasswordValidator(
    private val loginErrorString: LoginErrorString,
) : Validator<Pair<String, String>> {

    override fun validate(source: Pair<String, String>) {
        val password = source.first
        val repeatedPassword = source.second

        when {
            password.isBlank() -> throw ValidationException(loginErrorString.emptyPasswordField)
            repeatedPassword.isBlank() -> throw ValidationException(loginErrorString.emptyRepeatPasswordField)
            password.length < MINIMAL_PASSWORD_LENGTH -> throw ValidationException(loginErrorString.weakPassword)
            password != repeatedPassword -> throw ValidationException(loginErrorString.passwordsDontMatch)
        }
    }
}
