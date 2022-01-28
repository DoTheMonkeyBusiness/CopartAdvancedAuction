package com.cprt.advancedauction.logIn.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.cprt.advancedauction.core.screen.LogInScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class LogInScreenUI : LogInScreen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column {
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
}