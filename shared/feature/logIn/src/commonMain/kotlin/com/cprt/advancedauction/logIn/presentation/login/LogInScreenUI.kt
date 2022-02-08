package com.cprt.advancedauction.logIn.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screen.tools.LogInScreen
import com.cprt.advancedauction.foundation.button.AAMainButton
import com.cprt.advancedauction.foundation.button.AAOutlinedButton
import com.cprt.advancedauction.foundation.checkbox.AACheckBoxWithText
import com.cprt.advancedauction.foundation.spacer.HSpacer
import com.cprt.advancedauction.logIn.foundation.CommonAdditionalBlock
import com.cprt.advancedauction.logIn.foundation.CommonHeaderBlock
import com.cprt.advancedauction.logIn.foundation.CommonWindow
import com.cprt.advancedauction.logIn.foundation.textfield.EmailInputField
import com.cprt.advancedauction.logIn.foundation.textfield.PasswordInputField
import com.cprt.advancedauction.logIn.presentation.registration.RegistrationScreenUI
import com.cprt.advancedauction.logIn.presentation.reset.ResetPasswordScreenUI

class LogInScreenUI : LogInScreen {

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
                        description = "Don't have an account?",
                        action = "Sign up",
                        onActionClick = {
                            navigator.push(RegistrationScreenUI())
                        }
                    )
                }
            ) {
                LoginBlock()
            }
        }
    }

    @Composable
    private fun LoginBlock() {
        CommonHeaderBlock(
            title = "Sign in",
            description = "Login to manage your account",
        )
        HSpacer(16.dp)
        InputsBlock()
        HSpacer(8.dp)
        ActionsBlock()
        HSpacer(24.dp)
        ButtonsBlock()
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun InputsBlock() {
        val (emailRef, passwordRef) = FocusRequester.createRefs()

        EmailInputField(
            modifier = Modifier.focusOrder(emailRef) {
                next = passwordRef
            },
            onValueChange = {}
        )
        HSpacer(8.dp)
        PasswordInputField(
            modifier = Modifier.focusOrder(passwordRef),
            label = "Password",
            onValueChange = {},
        )

        LaunchedEffect(Unit) {
            emailRef.requestFocus()
        }
    }

    @Composable
    private fun ButtonsBlock() {
        AAMainButton(
            text = "Sign in",
            onClick = {},
        )
        HSpacer(6.dp)
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Or",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
        HSpacer(6.dp)
        AAOutlinedButton(
            text = "Skip",
            onClick = {},
        )
    }

    @Composable
    private fun ActionsBlock() {
        var isRememberEnabled by remember { mutableStateOf(true) }
        val navigator = LocalNavigator.currentOrThrow

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AACheckBoxWithText(
                checked = isRememberEnabled,
                onCheckedChange = { isRememberEnabled = !isRememberEnabled },
                text = "Remember me",
            )
            ClickableText(
                text = AnnotatedString("Forgot password?"),
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
                onClick = {
                    navigator.push(ResetPasswordScreenUI())
                }
            )
        }
    }
}
