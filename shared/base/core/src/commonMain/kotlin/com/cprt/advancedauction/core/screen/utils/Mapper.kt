package com.cprt.advancedauction.core.screen.utils

interface Mapper<in From, out To> {
    fun map(from: From): To
}
