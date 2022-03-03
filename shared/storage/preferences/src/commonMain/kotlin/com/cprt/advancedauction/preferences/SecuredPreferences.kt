package com.cprt.advancedauction.preferences

import com.cprt.advancedauction.core.screen.AppPreferences
import com.cprt.advancedauction.preferences.provider.SecuredPreferencesProvider
import com.cprt.advancedauction.preferences.provider.stringProperty

internal class SecuredPreferences(provider: SecuredPreferencesProvider) : AppPreferences.Secured {

    override var accessToken: String by provider.stringProperty("")

    override var refreshToken: String by provider.stringProperty("")

    override var email: String by provider.stringProperty("")

    override var password: String by provider.stringProperty("")
}
