package com.cprt.advancedauction.core

sealed interface AppPreferences {
    interface Default : AppPreferences {
        var isAnonUser: Boolean
        var tokenExpirationDate: Long
    }

    interface Secured : AppPreferences {
        var accessToken: String
        var refreshToken: String
        var email: String
        var password: String
    }
}
