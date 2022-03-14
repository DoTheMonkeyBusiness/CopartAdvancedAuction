package com.cprt.advancedauction.auth

import com.cprt.advancedauction.auth.domain.model.TokenInfoModel
import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel

interface Authentication {

    suspend fun signInWithEmail(
        userCredentials: UserCredentialsModel,
        isGuest: Boolean,
    )

    suspend fun signInAnon()

    suspend fun signUp(
        userCredentials: UserCredentialsModel,
    )

    suspend fun updateToken(): TokenInfoModel

    fun signOut()
}
