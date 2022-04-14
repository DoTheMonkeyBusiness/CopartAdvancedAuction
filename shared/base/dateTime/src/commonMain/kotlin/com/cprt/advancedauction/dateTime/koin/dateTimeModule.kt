package com.cprt.advancedauction.dateTime.koin

import com.cprt.advancedauction.dateTime.DateTime
import com.cprt.advancedauction.dateTime.KotlinDateTime
import org.koin.dsl.module

val dateTimeModule = module {
    single<DateTime> {
        KotlinDateTime()
    }
}
