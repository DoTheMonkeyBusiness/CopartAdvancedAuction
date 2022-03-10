package com.cprt.advancedauction.logIn.presentation.reset

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.TextFieldValue
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
            CommonWindow(
                navigator = navigator,
            ) {
                ResetPasswordBlock()
            }
        }
    }

    @Composable
    private fun ResetPasswordBlock() {
        val focusRequester = remember { FocusRequester() }

        CommonHeaderBlock(
            title = "Forgot password",
            description = "Please enter your registration email address. We'll send instructions to help reset your password.",
        )
        HSpacer(16.dp)
        EmailInputField(
            modifier = Modifier.focusRequester(focusRequester),
            value = TextFieldValue(),
            onValueChange = {},
        )
        HSpacer(24.dp)
        AAMainButton(
            text = "Send reset instructions",
            onClick = {},
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}
