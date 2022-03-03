package com.cprt.advancedauction.splash.domain.repository

internal interface LoginRepository {

    suspend fun isNeedToLogin(): Boolean
}
