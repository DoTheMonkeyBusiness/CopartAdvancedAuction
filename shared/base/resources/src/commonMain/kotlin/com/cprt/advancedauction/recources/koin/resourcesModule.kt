package com.cprt.advancedauction.recources.koin

import com.cprt.advancedauction.core.resources.appString.LoginErrorString
import com.cprt.advancedauction.recources.appString.LoginError
import org.koin.dsl.module

val resourcesModule = module {
    single<LoginErrorString> {
        LoginError()
    }
}
