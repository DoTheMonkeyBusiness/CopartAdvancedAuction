package com.cprt.advancedauction.firebaseauth.data.service

import com.cprt.advancedauction.core.screen.service.ApiService
import com.cprt.advancedauction.firebaseauth.data.requestBodyEntity.CredentialsRequestBody
import com.cprt.advancedauction.firebaseauth.data.requestBodyEntity.RefreshTokenRequestBody
import com.cprt.advancedauction.firebaseauth.data.responseEntity.RefreshTokenInfoEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.TokenInfoEntity

sealed interface AuthService {

    interface SignIn : AuthService, ApiService<CredentialsRequestBody, TokenInfoEntity>

    interface SignUp : AuthService, ApiService<CredentialsRequestBody, TokenInfoEntity>

    interface RefreshToken : AuthService, ApiService<RefreshTokenRequestBody, RefreshTokenInfoEntity>
}
