package com.cprt.advancedauction.security.koin

import com.cprt.advancedauction.security.encryption.Encryptor
import com.cprt.advancedauction.security.encryption.EncryptorAES
import org.koin.dsl.module

val securityModule = module {
    factory<Encryptor.AES> { EncryptorAES() }
}
