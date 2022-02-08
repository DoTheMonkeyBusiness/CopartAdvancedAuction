package com.cprt.advancedauction.logIn.presentation.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screen.tools.RegistrationScreen
import com.cprt.advancedauction.foundation.button.AAMainButton
import com.cprt.advancedauction.foundation.spacer.HSpacer
import com.cprt.advancedauction.logIn.foundation.CommonAdditionalBlock
import com.cprt.advancedauction.logIn.foundation.CommonHeaderBlock
import com.cprt.advancedauction.logIn.foundation.CommonWindow
import com.cprt.advancedauction.logIn.foundation.textfield.EmailInputField
import com.cprt.advancedauction.logIn.foundation.textfield.PasswordInputField
import com.cprt.advancedauction.logIn.presentation.login.LogInScreenUI

class RegistrationScreenUI : RegistrationScreen {

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
            CommonWindow(
                additionalContent = {
                    CommonAdditionalBlock(
                        description = "Already have an account?",
                        action = "Sign in",
                        onActionClick = {
                            navigator.push(LogInScreenUI())
                        }
                    )
                }
            ) {
                RegistrationBlock()
            }
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun RegistrationBlock() {
        CommonHeaderBlock(
            title = "Create your Account",
            description = "Register to access user-specific features.",
        )
        HSpacer(16.dp)
        InputsBlock()
        HSpacer(24.dp)
        AAMainButton(
            text = "Create my account",
            onClick = {},
        )
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun InputsBlock() {
        val (emailRef, passwordRef, repeatPasswordRef) = FocusRequester.createRefs()

        EmailInputField(
            modifier = Modifier.focusOrder(emailRef) {
                next = passwordRef
            },
            onValueChange = {}
        )
        HSpacer(8.dp)
        PasswordInputField(
            modifier = Modifier.focusOrder(passwordRef) {
                next = repeatPasswordRef
            },
            label = "Password",
            onValueChange = {},
        )
        HSpacer(8.dp)
        PasswordInputField(
            modifier = Modifier.focusOrder(repeatPasswordRef),
            label = "Repeat password",
            onValueChange = {},
        )

        LaunchedEffect(Unit) {
            emailRef.requestFocus()
        }
    }
}
