package com.cprt.advancedauction.preferences.koin

import com.cprt.advancedauction.core.AppPreferences
import com.cprt.advancedauction.preferences.DefaultPreferences
import com.cprt.advancedauction.preferences.SecuredPreferences
import com.cprt.advancedauction.preferences.provider.PreferencesProvider
import com.cprt.advancedauction.preferences.provider.SecuredPreferencesProvider
import com.cprt.advancedauction.preferences.util.Constants
import com.cprt.advancedauction.preferences.util.Constants.SECURED_PREFERENCES_NAME
import org.koin.dsl.module

val preferencesModule = module {
    single {
        SecuredPreferencesProvider(
            name = SECURED_PREFERENCES_NAME,
            encryptor = get()
        )
    }
    single {
        PreferencesProvider(
            name = Constants.DEFAULT_PREFERENCES_NAME,
        )
    }

    single<AppPreferences.Default> {
        DefaultPreferences(provider = get())
    }
    single<AppPreferences.Secured> {
        SecuredPreferences(provider = get())
    }
}
