package com.cprt.advancedauction.firebaseauth.data.service.passwordReset

import com.cprt.advancedauction.core.screen.service.ApiService
import com.cprt.advancedauction.firebaseauth.data.requestEntity.ResetEmailRequestEntity
import com.cprt.advancedauction.firebaseauth.data.responseEntity.ResetCodeResponseEntity

sealed interface PasswordResetService {

    interface SendResetEmail : PasswordResetService, ApiService<ResetEmailRequestEntity, ResetCodeResponseEntity>
}
