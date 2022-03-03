package com.cprt.advancedauction.firebaseauth.koin

import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.auth.Authentication
import com.cprt.advancedauction.firebaseauth.FirebaseAuthTokenHolder
import com.cprt.advancedauction.firebaseauth.FirebaseAuthentication
import com.cprt.advancedauction.firebaseauth.FirebaseClientProvider
import com.cprt.advancedauction.firebaseauth.data.service.AuthService
import com.cprt.advancedauction.firebaseauth.data.service.RefreshTokenService
import com.cprt.advancedauction.firebaseauth.data.service.SignInService
import com.cprt.advancedauction.firebaseauth.data.service.SignUpService
import com.cprt.advancedauction.firebaseauth.util.AuthUtil
import org.koin.dsl.module

val firebaseModule = module {
    factory {
        AuthUtil(
            defaultPreferences = get(),
            securedPreferences = get(),
        )
    }
    factory<AuthService.SignIn> { SignInService(client = get()) }
    factory<AuthService.SignUp> { SignUpService(client = get()) }
    factory<AuthService.RefreshToken> { RefreshTokenService(client = get()) }

    single {
        FirebaseClientProvider(
            authTokenHolder = get(),
            authUtil = get(),
        ).getHttpClient()
    }
    single<Authentication> {
        FirebaseAuthentication(
            authTokenHolder = get(),
            authUtil = get(),
            signInService = get(),
            signUpService = get(),
            refreshTokenService = get(),
        )
    }
    single<AuthTokenHolder> {
        FirebaseAuthTokenHolder(
            defaultPreferences = get(),
            securedPreferences = get(),
        )
    }
}