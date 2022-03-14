package com.cprt.advancedauction.logIn.domain.repository

import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.logIn.domain.model.SignInModel

internal interface LoginRepository {

    suspend fun signIn(signInModel: SignInModel)

    suspend fun signUp(userCredentials: UserCredentialsModel)

    suspend fun skipSignIn()
}
