package com.cprt.advancedauction.firebaseauth

import com.cprt.advancedauction.auth.domain.model.UserLoginState
import com.cprt.advancedauction.core.screen.AppPreferences
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.spyk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@ExtendWith(MockKExtension::class)
internal class FirebaseAuthTokenHolderTest {

    @RelaxedMockK
    private lateinit var defaultPreferences: AppPreferences.Default

    @RelaxedMockK
    private lateinit var securedPreferences: AppPreferences.Secured

    @Test
    fun anonStateCheck() {
        coEvery { defaultPreferences.isAnonUser } returns true

        val firebaseAuthTokenHolder = spyk(
            FirebaseAuthTokenHolder(
                defaultPreferences = defaultPreferences,
                securedPreferences = securedPreferences
            )
        )

        val loginState = firebaseAuthTokenHolder.userLoginState.value

        assert(loginState is UserLoginState.Anon)
    }

    @Test
    fun loggedInStateCheck() {
        coEvery { defaultPreferences.isAnonUser } returns false
        coEvery { securedPreferences.email } returns "notEmpty"
        coEvery { securedPreferences.password } returns "notEmpty"

        val firebaseAuthTokenHolder = spyk(
            FirebaseAuthTokenHolder(
                defaultPreferences = defaultPreferences,
                securedPreferences = securedPreferences
            )
        )

        val loginState = firebaseAuthTokenHolder.userLoginState.value

        assert(loginState is UserLoginState.LoggedIn)
    }

    @ParameterizedTest
    @CsvSource(
        "notEmpty, ''",
        "'', notEmpty",
        "'', ''",
    )
    fun guestStateCheck(email: String, password: String) {
        coEvery { defaultPreferences.isAnonUser } returns false
        coEvery { securedPreferences.email } returns email
        coEvery { securedPreferences.password } returns password

        val firebaseAuthTokenHolder = spyk(
            FirebaseAuthTokenHolder(
                defaultPreferences = defaultPreferences,
                securedPreferences = securedPreferences
            )
        )

        val loginState = firebaseAuthTokenHolder.userLoginState.value

        assert(loginState is UserLoginState.Guest)
    }
}
