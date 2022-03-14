package com.cprt.advancedauction.logIn.utils

import com.cprt.advancedauction.firebaseauth.exception.LoginException

internal fun Throwable.getErrorMessage(defaultMessage: String): String {
    return if (this is LoginException) {
        errorMessage
    } else {
        defaultMessage
    }
}