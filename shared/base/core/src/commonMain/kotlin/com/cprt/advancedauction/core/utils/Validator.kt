package com.cprt.advancedauction.core.utils

import com.cprt.advancedauction.core.exception.ValidationException

interface Validator<in Source>  {

    @Throws(ValidationException::class)
    fun validate(source: Source)
}
