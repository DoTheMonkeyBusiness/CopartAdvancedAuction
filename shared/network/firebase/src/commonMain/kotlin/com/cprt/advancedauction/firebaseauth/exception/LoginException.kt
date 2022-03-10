package com.cprt.advancedauction.firebaseauth.exception

class LoginException(
    exceptionMessage: String?,
    val errorMessage: String,
) : RuntimeException(exceptionMessage)
