package com.cprt.advancedauction.preferences.koin

import com.cprt.advancedauction.preferences.provider.DefaultPreferencesProvider
import com.cprt.advancedauction.preferences.provider.PreferencesProvider
import com.cprt.advancedauction.preferences.util.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal actual val additionalModule = module {
    single<PreferencesProvider.Default> {
        DefaultPreferencesProvider(
            name = Constants.DEFAULT_PREFERENCES_NAME,
            context = androidApplication(),
        )
    }
}
