package com.cprt.advancedauction.logIn.presentation.reset

import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.foundation.button.AAMainButton
import com.cprt.advancedauction.foundation.spacer.HSpacer
import com.cprt.advancedauction.logIn.foundation.CommonHeaderBlock
import com.cprt.advancedauction.logIn.foundation.CommonWindow
import com.cprt.advancedauction.logIn.foundation.textfield.EmailInputField

internal class ResetPasswordScreenUI : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Surface {
            Button(
                onClick = {
                    navigator.pop()
                }
            ) {
                Text("pop")
            }
            CommonWindow {
                ResetPasswordBlock()
            }
        }
    }

    @Composable
    private fun ResetPasswordBlock() {
        CommonHeaderBlock(
            title = "Forgot password",
            description = "Please enter your registration email address. We'll send instructions to help reset your password.",
        )
        HSpacer(16.dp)
        EmailInputField(
            onValueChange = {}
        )
        HSpacer(24.dp)
        AAMainButton(
            text = "Send reset instructions",
            onClick = {},
        )
    }
}
