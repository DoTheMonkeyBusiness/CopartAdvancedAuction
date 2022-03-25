package com.cprt.advancedauction.preferences

import com.cprt.advancedauction.core.AppPreferences
import com.cprt.advancedauction.preferences.provider.PreferencesProvider
import com.cprt.advancedauction.preferences.util.booleanProperty
import com.cprt.advancedauction.preferences.util.longProperty

internal class DefaultPreferences(provider: PreferencesProvider.Default) : AppPreferences.Default {

    override var isAnonUser: Boolean by provider.booleanProperty(false)

    override var tokenExpirationDate: Long by provider.longProperty(0L)
}
