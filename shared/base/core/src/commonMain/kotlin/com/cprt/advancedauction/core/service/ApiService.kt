package com.cprt.advancedauction.core.service

interface ApiService<in P, out R> {
    suspend fun load(param: P): R
}
