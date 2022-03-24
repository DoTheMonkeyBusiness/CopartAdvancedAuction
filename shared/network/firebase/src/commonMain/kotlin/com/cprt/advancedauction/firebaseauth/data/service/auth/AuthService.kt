package com.cprt.advancedauction.firebaseauth.data.service.auth

import com.cprt.advancedauction.core.service.ApiService
import com.cprt.advancedauction.firebaseauth.data.requestEntity.CredentialsRequestEntity
import com.cprt.advancedauction.firebaseauth.data.requestEntity.RefreshTokenRequestEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.RefreshTokenResponseEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.TokenInfoResponseEntity

sealed interface AuthService {

    interface SignIn : AuthService, ApiService<CredentialsRequestEntity, TokenInfoResponseEntity>

    interface SignUp : AuthService, ApiService<CredentialsRequestEntity, TokenInfoResponseEntity>

    interface RefreshToken : AuthService, ApiService<RefreshTokenRequestEntity, RefreshTokenResponseEntity>
}
