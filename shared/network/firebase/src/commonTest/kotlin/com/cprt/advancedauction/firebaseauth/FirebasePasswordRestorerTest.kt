package com.cprt.advancedauction.firebaseauth

import com.cprt.advancedauction.firebaseauth.data.responseEntity.ResetCodeResponseEntity
import com.cprt.advancedauction.firebaseauth.data.service.passwordReset.PasswordResetService
import com.cprt.advancedauction.firebaseauth.util.mapper.SendPasswordChangeEmailErrorMapper
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class FirebasePasswordRestorerTest {

    @RelaxedMockK
    private lateinit var sendResetCodeErrorMapper: SendPasswordChangeEmailErrorMapper

    @RelaxedMockK
    private lateinit var sendResetCodeService: PasswordResetService.SendResetEmail

    @RelaxedMockK
    private lateinit var json: Json

    @InjectMockKs
    private lateinit var firebasePasswordRestorer: FirebasePasswordRestorer

    @Test
    fun sendPasswordResetEmailTest() = runBlocking {
        coEvery { sendResetCodeService.load(any()) } returns ResetCodeResponseEntity("email")

        assertDoesNotThrow { firebasePasswordRestorer.sendPasswordResetEmail("email") }
    }
}
