package com.cprt.advancedauction.core.screen.utils

val String.withoutWhiteSpaces: String
    get() = filter { !it.isWhitespace() }
