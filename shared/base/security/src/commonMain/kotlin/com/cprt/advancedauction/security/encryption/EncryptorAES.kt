package com.cprt.advancedauction.security.encryption

import com.cprt.advancedauction.SecurityConfig
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

private const val ALGORITHM = "AES"

internal class EncryptorAES : Encryptor.AES {

    private val cipherEncryptor by lazy {
        getCipher(Cipher.ENCRYPT_MODE)
    }

    private val cipherDecryptor by lazy {
        getCipher(Cipher.DECRYPT_MODE)
    }

    override fun encrypt(plainText: String): String {
        val encodedValue = cipherEncryptor.doFinal(plainText.toByteArray())
        return Base64.getEncoder().encodeToString(encodedValue)
    }

    override fun decrypt(encodedText: String): String {
        val decodedValue = Base64.getDecoder().decode(encodedText)
        val decValue = cipherDecryptor.doFinal(decodedValue)
        return String(decValue)
    }

    private fun getCipher(mode: Int): Cipher = Cipher.getInstance(ALGORITHM).apply {
        init(mode, SecretKeySpec(SecurityConfig.SECRET_SALT_KEY.toByteArray(), ALGORITHM))
    }
}
