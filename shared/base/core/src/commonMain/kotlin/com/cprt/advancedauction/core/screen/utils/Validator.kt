package com.cprt.advancedauction.core.screen.utils

import com.cprt.advancedauction.core.screen.exception.ValidationException

interface Validator<in Source>  {

    @Throws(ValidationException::class)
    fun validate(source: Source)
}
