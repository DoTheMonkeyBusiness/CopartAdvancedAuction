package com.cprt.advancedauction.common.koin

import com.cprt.advancedauction.common.tool.AppScreenProvider
import com.cprt.advancedauction.core.screen.tools.ScreenProvider
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
) = startKoin {
    appDeclaration()
    modules(
        commonModule
    )
}

internal val commonModule = module {
    factory<ScreenProvider> { AppScreenProvider() }
}