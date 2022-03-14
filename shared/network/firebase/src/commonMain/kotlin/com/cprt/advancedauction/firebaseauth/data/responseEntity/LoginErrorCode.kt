package com.cprt.advancedauction.firebaseauth.data.responseEntity

enum class SignInErrorCode {
    EMAIL_NOT_FOUND,
    INVALID_EMAIL,
    INVALID_PASSWORD,
    USER_DISABLED,
}

enum class SignUpErrorCode {
    EMAIL_EXISTS,
    INVALID_EMAIL,
    TOO_MANY_ATTEMPTS_TRY_LATER,
}

enum class AnonErrorCode {
    ADMIN_ONLY_OPERATION,
    OPERATION_NOT_ALLOWED,
}
