package com.cprt.advancedauction.android

import android.app.Application
import com.cprt.advancedauction.common.koin.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class AdvancedFilterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@AdvancedFilterApplication)
        }
    }
}
