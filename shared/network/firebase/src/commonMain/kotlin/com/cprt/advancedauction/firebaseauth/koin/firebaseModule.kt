package com.cprt.advancedauction.firebaseauth.koin

import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.auth.Authentication
import com.cprt.advancedauction.auth.PasswordRestorer
import com.cprt.advancedauction.firebaseauth.FirebaseAuthTokenHolder
import com.cprt.advancedauction.firebaseauth.FirebaseAuthentication
import com.cprt.advancedauction.firebaseauth.FirebasePasswordRestorer
import com.cprt.advancedauction.firebaseauth.data.FirebaseClientProvider
import com.cprt.advancedauction.firebaseauth.data.service.auth.AuthService
import com.cprt.advancedauction.firebaseauth.data.service.auth.RefreshTokenService
import com.cprt.advancedauction.firebaseauth.data.service.auth.SignInService
import com.cprt.advancedauction.firebaseauth.data.service.auth.SignUpService
import com.cprt.advancedauction.firebaseauth.data.service.passwordReset.PasswordResetService
import com.cprt.advancedauction.firebaseauth.data.service.passwordReset.SendResetEmailService
import com.cprt.advancedauction.firebaseauth.util.AuthUtil
import com.cprt.advancedauction.firebaseauth.util.mapper.*
import org.koin.dsl.module

val firebaseModule = module {
    factory {
        AuthUtil(
            defaultPreferences = get(),
            securedPreferences = get(),
        )
    }
    factory {
        AnonSignInErrorMapper(
            loginErrorString = get()
        )
    }
    factory {
        SendPasswordChangeEmailErrorMapper(
            loginErrorString = get()
        )
    }
    factory {
        SignInErrorMapper(
            loginErrorString = get()
        )
    }
    factory {
        SignUpErrorMapper(
            loginErrorString = get()
        )
    }
    factory<AuthService.SignIn> {
        SignInService(client = get())
    }
    factory<AuthService.SignUp> {
        SignUpService(client = get())
    }
    factory<AuthService.RefreshToken> {
        RefreshTokenService(client = get())
    }
    factory<PasswordResetService.SendResetEmail> {
        SendResetEmailService(client = get())
    }
    factory<PasswordRestorer> {
        FirebasePasswordRestorer(
            sendResetCodeErrorMapper = get(),
            sendResetCodeService = get(),
            json = get(),
        )
    }

    single {
        FirebaseClientProvider(
            authTokenHolder = get(),
            authUtil = get(),
            json = get(),
        ).getHttpClient()
    }
    single<Authentication> {
        FirebaseAuthentication(
            authTokenHolder = get(),
            authUtil = get(),
            json = get(),
            anonSignInErrorMapper = get(),
            loginErrorMapper = get(),
            signUpErrorMapper = get(),
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
