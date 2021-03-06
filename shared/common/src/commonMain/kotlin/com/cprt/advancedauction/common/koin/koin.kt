package com.cprt.advancedauction.common.koin

import com.cprt.advancedauction.common.tool.AppScreenProvider
import com.cprt.advancedauction.navigation.tools.ScreenProvider
import com.cprt.advancedauction.dateTime.koin.dateTimeModule
import com.cprt.advancedauction.firebaseauth.koin.firebaseModule
import com.cprt.advancedauction.internalNotification.koin.internalNotificationModule
import com.cprt.advancedauction.logIn.koin.logInModule
import com.cprt.advancedauction.main.koin.mainModule
import com.cprt.advancedauction.preferences.koin.preferencesModule
import com.cprt.advancedauction.recources.koin.resourcesModule
import com.cprt.advancedauction.security.koin.securityModule
import com.cprt.advancedauction.splash.koin.splashModule
import com.cprt.advancedauction.vehicleSearch.koin.vehicleSearchModule
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
) = startKoin {
    appDeclaration()
    modules(
        commonModule,
        dateTimeModule,
        firebaseModule,
        internalNotificationModule,
        logInModule,
        mainModule,
        preferencesModule,
        resourcesModule,
        securityModule,
        splashModule,
        vehicleSearchModule,
    )
}

internal val commonModule = module {
    factory<ScreenProvider> { AppScreenProvider() }

    single {
        Json {
            ignoreUnknownKeys = true
        }
    }
}