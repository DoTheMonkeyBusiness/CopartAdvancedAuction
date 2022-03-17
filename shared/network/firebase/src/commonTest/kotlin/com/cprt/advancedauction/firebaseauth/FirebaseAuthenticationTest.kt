package com.cprt.advancedauction.firebaseauth

import com.cprt.advancedauction.auth.AuthTokenHolder
import com.cprt.advancedauction.auth.domain.model.TokenInfoModel
import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.auth.domain.model.UserLoginState
import com.cprt.advancedauction.firebaseauth.data.responseEntity.RefreshTokenResponseEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.TokenInfoResponseEntity
import com.cprt.advancedauction.firebaseauth.data.service.auth.AuthService
import com.cprt.advancedauction.firebaseauth.util.AuthUtil
import com.cprt.advancedauction.firebaseauth.util.mapper.AnonSignInErrorMapper
import com.cprt.advancedauction.firebaseauth.util.mapper.SignInErrorMapper
import com.cprt.advancedauction.firebaseauth.util.mapper.SignUpErrorMapper
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

private const val ID_TOKEN = "idToken"
private const val REFRESH_TOKEN = "refreshToken"
private const val EXPIRES_IN = "123"

@ExtendWith(MockKExtension::class)
internal class FirebaseAuthenticationTest {

    @RelaxedMockK
    private lateinit var authTokenHolder: AuthTokenHolder

    @RelaxedMockK
    private lateinit var authUtil: AuthUtil

    @RelaxedMockK
    private lateinit var json: Json

    @RelaxedMockK
    private lateinit var anonSignInErrorMapper: AnonSignInErrorMapper

    @RelaxedMockK
    private lateinit var loginErrorMapper: SignInErrorMapper

    @RelaxedMockK
    private lateinit var signUpErrorMapper: SignUpErrorMapper

    @RelaxedMockK
    private lateinit var signInService: AuthService.SignIn

    @RelaxedMockK
    private lateinit var signUpService: AuthService.SignUp

    @RelaxedMockK
    private lateinit var refreshTokenService: AuthService.RefreshToken

    @InjectMockKs
    private lateinit var firebaseAuthentication: FirebaseAuthentication

    private val credentials = UserCredentialsModel("email", "password")

    private val tokenInfoModel = TokenInfoModel(ID_TOKEN, REFRESH_TOKEN, EXPIRES_IN.toLong())

    @Test
    fun signInUserSuccessTest() = runBlocking {
        coEvery { signInService.load(any()) } returns TokenInfoResponseEntity(ID_TOKEN, REFRESH_TOKEN, EXPIRES_IN)

        firebaseAuthentication.signInWithEmail(credentials, false)

        verify(exactly = 0) { authTokenHolder.setGuestState(tokenInfoModel) }
        verify(exactly = 1) { authTokenHolder.setLoggedInState(tokenInfoModel, credentials) }
        verify(exactly = 1) { authUtil.saveTokenIntoPreferences(tokenInfoModel) }
        verify(exactly = 1) { authUtil.saveCredentialsIntoPreferences(credentials) }
    }

    @Test
    fun signInGuestUserSuccessTest() = runBlocking {
        coEvery { signInService.load(any()) } returns TokenInfoResponseEntity(ID_TOKEN, REFRESH_TOKEN, EXPIRES_IN)

        firebaseAuthentication.signInWithEmail(credentials, true)

        verify(exactly = 1) { authTokenHolder.setGuestState(tokenInfoModel) }
        verify(exactly = 0) { authTokenHolder.setLoggedInState(tokenInfoModel, credentials) }
        verify(exactly = 1) { authUtil.saveTokenIntoPreferences(tokenInfoModel) }
        verify(exactly = 0) { authUtil.saveCredentialsIntoPreferences(credentials) }
    }

    @Test
    fun signInAnonTest() = runBlocking {
        coEvery { signUpService.load(any()) } returns TokenInfoResponseEntity(ID_TOKEN, REFRESH_TOKEN, EXPIRES_IN)

        firebaseAuthentication.signInAnon()

        verify(exactly = 1) { authTokenHolder.setAnonState(tokenInfoModel) }
        verify(exactly = 1) { authUtil.saveTokenIntoPreferences(tokenInfoModel) }
    }

    @Test
    fun signUpTest() = runBlocking {
        coEvery { signUpService.load(any()) } returns TokenInfoResponseEntity(ID_TOKEN, REFRESH_TOKEN, EXPIRES_IN)

        firebaseAuthentication.signUp(credentials)

        verify(exactly = 1) { authTokenHolder.setLoggedInState(tokenInfoModel, credentials) }
        verify(exactly = 1) { authUtil.saveCredentialsIntoPreferences(credentials) }
        verify(exactly = 1) { authUtil.saveTokenIntoPreferences(tokenInfoModel) }
    }

    @ParameterizedTest
    @MethodSource("loginStateGenerator")
    fun updateTokenTest(state: UserLoginState) = runBlocking {
        coEvery { refreshTokenService.load(any()) } returns RefreshTokenResponseEntity(ID_TOKEN, REFRESH_TOKEN, EXPIRES_IN)
        coEvery { authTokenHolder.userLoginState.value } returns state

        firebaseAuthentication.updateToken()

        verify(exactly = 1) { authUtil.saveTokenIntoPreferences(tokenInfoModel) }
    }

    @Test
    fun logOutTest() = runBlocking {
        firebaseAuthentication.signOut()

        verify(exactly = 1) { authUtil.clearTokens() }
        verify(exactly = 1) { authUtil.clearCredentials() }
    }

    companion object {

        @JvmStatic
        fun loginStateGenerator(): Stream<UserLoginState> {
            val tokenInfoModel = TokenInfoModel(ID_TOKEN, REFRESH_TOKEN, EXPIRES_IN.toLong())
            val credentials = UserCredentialsModel("email", "password")

            return Stream.of(
                UserLoginState.LoggedIn(tokenInfoModel, credentials),
                UserLoginState.Anon(tokenInfoModel),
                UserLoginState.Guest(tokenInfoModel),
            )
        }
    }
}
