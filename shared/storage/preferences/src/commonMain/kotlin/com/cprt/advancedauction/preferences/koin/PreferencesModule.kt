package com.cprt.advancedauction.preferences.koin

import com.cprt.advancedauction.core.AppPreferences
import com.cprt.advancedauction.preferences.DefaultPreferences
import com.cprt.advancedauction.preferences.SecuredPreferences
import com.cprt.advancedauction.preferences.provider.PreferencesProvider
import com.cprt.advancedauction.preferences.provider.SecuredPreferencesProvider
import org.koin.core.module.Module
import org.koin.dsl.module

val preferencesModule = module {
    includes(additionalModule)

    single<PreferencesProvider.Secured> {
        SecuredPreferencesProvider(
            preferences = get(),
            encryptor = get()
        )
    }

    single<AppPreferences.Default> {
        DefaultPreferences(provider = get())
    }
    single<AppPreferences.Secured> {
        SecuredPreferences(provider = get())
    }
}

internal expect val additionalModule: Module
