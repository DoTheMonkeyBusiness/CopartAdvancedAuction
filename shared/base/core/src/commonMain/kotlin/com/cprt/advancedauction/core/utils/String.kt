package com.cprt.advancedauction.core.utils

val String.withoutWhiteSpaces: String
    get() = filter { !it.isWhitespace() }
