package com.cprt.advancedauction.core.screen.exception

class ValidationException(
    val explanation: String
) : RuntimeException(explanation)
