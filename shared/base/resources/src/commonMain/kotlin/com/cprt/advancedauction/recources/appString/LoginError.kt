package com.cprt.advancedauction.recources.appString

import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString

internal class LoginError : LoginErrorString {

    override val anonNotAllowed: String = "Anonymous user sign-in is disabled."

    override val emailNotFound: String = "There is no user record corresponding to this email."

    override val invalidPassword: String = "The password is invalid."

    override val userDisabled: String = "The user account has been disabled by an administrator."

    override val unspecified: String = "Something went wrong. Please try again"
}
