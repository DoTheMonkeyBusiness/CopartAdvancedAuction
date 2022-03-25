package com.cprt.advancedauction.preferences

import com.cprt.advancedauction.core.AppPreferences
import com.cprt.advancedauction.preferences.provider.PreferencesProvider
import com.cprt.advancedauction.preferences.util.stringProperty

internal class SecuredPreferences(provider: PreferencesProvider.Secured) : AppPreferences.Secured {

    override var accessToken: String by provider.stringProperty("")

    override var refreshToken: String by provider.stringProperty("")

    override var email: String by provider.stringProperty("")

    override var password: String by provider.stringProperty("")
}
