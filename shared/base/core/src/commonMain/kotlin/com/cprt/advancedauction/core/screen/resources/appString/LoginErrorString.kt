package com.cprt.advancedauction.core.screen.resources.appString

interface LoginErrorString : AppString {

    val anonNotAllowed: String

    val emailNotFound: String

    val emailExists: String

    val emailIsNotValid: String

    val emptyEmailField: String

    val emptyPasswordField: String

    val emptyRepeatPasswordField: String

    val invalidEmail: String

    val invalidPassword: String

    val passwordsDontMatch: String

    val tooManyAttempts: String

    val userDisabled: String

    val unspecified: String

    val weakPassword: String
}
