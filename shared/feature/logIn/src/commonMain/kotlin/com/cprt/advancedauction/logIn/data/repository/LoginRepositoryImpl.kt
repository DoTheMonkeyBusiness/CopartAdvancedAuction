package com.cprt.advancedauction.logIn.data.repository

import com.cprt.advancedauction.auth.Authentication
import com.cprt.advancedauction.logIn.domain.model.SignInModel
import com.cprt.advancedauction.logIn.domain.repository.LoginRepository

internal class LoginRepositoryImpl(
    private val authenticator: Authentication,
) : LoginRepository {

    override suspend fun signIn(signInModel: SignInModel) {
        authenticator.signInWithEmail(
            userCredentials = signInModel.userCredentials,
            isGuest = !signInModel.isRemember
        )
    }

    override suspend fun skipSignIn() {
        authenticator.signInAnon()
    }
}