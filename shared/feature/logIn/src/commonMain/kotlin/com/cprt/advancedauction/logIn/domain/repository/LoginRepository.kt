package com.cprt.advancedauction.logIn.domain.repository

import com.cprt.advancedauction.logIn.domain.model.SignInModel

internal interface LoginRepository {

    suspend fun signIn(signInModel: SignInModel)

    suspend fun skipSignIn()
}
