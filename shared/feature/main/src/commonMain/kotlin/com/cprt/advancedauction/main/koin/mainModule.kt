package com.cprt.advancedauction.main.koin

import com.cprt.advancedauction.main.presentation.HomeScreenModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        HomeScreenModel(
            authTokenHolder = get(),
        )
    }
}
