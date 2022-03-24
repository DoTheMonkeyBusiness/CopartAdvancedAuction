package com.cprt.advancedauction.preferences

import com.cprt.advancedauction.core.AppPreferences
import com.cprt.advancedauction.preferences.provider.PreferencesProvider
import com.cprt.advancedauction.preferences.provider.booleanProperty
import com.cprt.advancedauction.preferences.provider.longProperty

internal class DefaultPreferences(provider: PreferencesProvider) : AppPreferences.Default {

    override var isAnonUser: Boolean by provider.booleanProperty(false)

    override var tokenExpirationDate: Long by provider.longProperty(0L)
}
