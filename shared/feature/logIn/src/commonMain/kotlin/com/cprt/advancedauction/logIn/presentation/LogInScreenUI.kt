package com.cprt.advancedauction.logIn.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.cprt.advancedauction.core.screen.tools.LogInScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screen.ScreenSize
import com.cprt.advancedauction.core.screen.tools.LocalScreenSize

class LogInScreenUI : LogInScreen {

    @Composable
    override fun Content() {
        if (LocalScreenSize.current == ScreenSize.Large) {
            Row {
                Items()
            }
        } else {
            Column {
                Items()
            }
        }
    }

    @Composable
    private fun Items() {
        val navigator = LocalNavigator.currentOrThrow
        Button(
            onClick = {
                navigator.pop()
            }
        ){
            Text("pop")
        }
        Text("LogInScreenUI")
    }
}