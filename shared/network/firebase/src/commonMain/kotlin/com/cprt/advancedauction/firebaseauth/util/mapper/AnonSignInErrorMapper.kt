package com.cprt.advancedauction.firebaseauth.util.mapper

import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.screen.utils.Mapper
import com.cprt.advancedauction.firebaseauth.data.responseEntity.AnonErrorCode

internal class AnonSignInErrorMapper(
    private val loginErrorString: LoginErrorString
) : Mapper<AnonErrorCode?, String> {

    override fun map(from: AnonErrorCode?): String {
        return when (from) {
            AnonErrorCode.OPERATION_NOT_ALLOWED,
            AnonErrorCode.ADMIN_ONLY_OPERATION -> loginErrorString.anonNotAllowed
            else -> loginErrorString.unspecified
        }
    }
}
