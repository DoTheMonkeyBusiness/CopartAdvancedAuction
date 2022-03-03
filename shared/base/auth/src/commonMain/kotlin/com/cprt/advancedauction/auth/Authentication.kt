package com.cprt.advancedauction.auth

import com.cprt.advancedauction.auth.model.TokenInfoModel
import com.cprt.advancedauction.auth.model.UserCredentials

interface Authentication {

    suspend fun signInWithEmail(
        userCredentials: UserCredentials,
        isGuest: Boolean,
    )

    suspend fun signInAnon()

    suspend fun updateToken(): TokenInfoModel

    fun signOut()
}
