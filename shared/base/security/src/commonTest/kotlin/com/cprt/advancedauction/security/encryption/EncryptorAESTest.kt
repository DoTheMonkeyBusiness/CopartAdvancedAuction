package com.cprt.advancedauction.security.encryption

import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class EncryptorAESTest {

    @SpyK
    private var encryptorAES = EncryptorAES()

    @ParameterizedTest
    @ValueSource(
        strings = [
            "abc",
            "123",
            "12.3",
            "-123",
            "sdasdsdfdjksfjksdjnsdjfnskjdnfnsfsdjksdnjkcsdjfsfkjnskjdfnsdfkjsfjnk"
        ]
    )
    fun testEncryption(input: String) {
        val encrypted = encryptorAES.encrypt(input)
        val decrypted = encryptorAES.decrypt(encrypted)

        assertEquals(input, decrypted)
    }
}
