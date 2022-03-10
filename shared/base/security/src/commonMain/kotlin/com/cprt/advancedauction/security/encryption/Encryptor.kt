package com.cprt.advancedauction.security.encryption

sealed interface Encryptor {

    fun encrypt(plainText: String): String

    fun decrypt(encodedText: String): String

    interface AES : Encryptor
}
