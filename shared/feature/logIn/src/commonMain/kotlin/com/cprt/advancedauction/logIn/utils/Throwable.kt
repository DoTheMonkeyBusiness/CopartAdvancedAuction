package com.cprt.advancedauction.logIn.utils

import com.cprt.advancedauction.core.exception.ValidationException
import com.cprt.advancedauction.firebaseauth.exception.LoginException

internal fun Throwable.getLoginErrorMessage(defaultMessage: String): String {
    return when (this) {
        is ValidationException -> explanation
        is LoginException -> errorMessage
        else -> defaultMessage
    }
}
