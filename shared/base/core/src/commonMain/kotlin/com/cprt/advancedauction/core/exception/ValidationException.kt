package com.cprt.advancedauction.core.exception

class ValidationException(
    val explanation: String
) : RuntimeException(explanation)
