package com.cprt.advancedauction.core.utils

interface Mapper<in From, out To> {
    fun map(from: From): To
}
