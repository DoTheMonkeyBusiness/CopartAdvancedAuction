package com.cprt.advancedauction.android

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cprt.advancedauction.common.App

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disablePhoneRotation()
        setContent {
            App()
        }
    }

    private fun disablePhoneRotation() {
        val isTablet = resources.getBoolean(R.bool.isTablet)

        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }
}
