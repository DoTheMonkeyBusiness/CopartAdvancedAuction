package com.cprt.advancedauction.recources.appString

import com.cprt.advancedauction.core.resources.appString.LoginErrorString

internal class LoginError : LoginErrorString {

    override val anonNotAllowed: String = "Anonymous user sign-in is disabled."

    override val emailNotFound: String = "There is no user record corresponding to this email."

    override val emailExists: String = "The email address is already in use by another account."

    override val emailIsNotValid: String = "The email address is not valid, please double-check it."

    override val emptyEmailField: String = "Email field is empty, please enter an email address."

    override val emptyPasswordField: String = "Password field is empty, please enter a password."

    override val emptyRepeatPasswordField: String = "Please enter the password in the repeat password field."

    override val invalidEmail: String = "Email is not valid, please recheck it."

    override val invalidPassword: String = "The password is invalid."

    override val passwordsDontMatch: String = "Passwords do not match, please check the entered data."

    override val tooManyAttempts: String = "We have blocked all requests from this device due to unusual activity. Try again later."

    override val userDisabled: String = "The user account has been disabled by an administrator."

    override val unspecified: String = "Something went wrong. Please try again"

    override val weakPassword: String = "Password length should be at least 6 characters."
}
