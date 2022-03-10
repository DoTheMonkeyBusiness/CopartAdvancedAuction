package com.cprt.advancedauction.logIn.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screen.screenModel.getScreenModel
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

class LogInScreenUI : LogInScreen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<LoginScreenModel>()
        val state by screenModel.stateFlow.collectAsState()
        var isNeedToShowProgress: Boolean by remember { mutableStateOf(false) }

        when (val currentState = state) {
            is LoginScreenModel.State.Idle -> isNeedToShowProgress = false
            is LoginScreenModel.State.SignInProgress,
            is LoginScreenModel.State.SkipSignInProgress -> isNeedToShowProgress = true
            is LoginScreenModel.State.SignInSuccess -> {
                screenModel.goHome(navigator)
            }
            is LoginScreenModel.State.SignInError -> {
                println("error message = ${currentState.message}")
                screenModel.setIdleState()
            }
        }

        Surface {
            CommonWindow(
                navigator = navigator,
                isNeedToShowProgress = isNeedToShowProgress,
                additionalContent = {
                    CommonAdditionalBlock(
                        description = "Don't have an account?",
                        action = "Sign up",
                        onActionClick = {
                            screenModel.goRegistration(navigator)
                        }
                    )
                }
            ) {
                LoginBlock(
                    screenModel = screenModel,
                    navigator = navigator
                )
            }
        }
    }

    @Composable
    private fun LoginBlock(
        screenModel: LoginScreenModel,
        navigator: Navigator,
    ) {
        CommonHeaderBlock(
            title = "Sign in",
            description = "Login to manage your account",
        )
        HSpacer(16.dp)
        InputsBlock(
            loginField = screenModel.loginField,
            passwordField = screenModel.passwordField,
            onLoginChanged = screenModel::emailInputChanged,
            onPasswordChanged = screenModel::passwordInputChanged,
        )
        HSpacer(8.dp)
        ActionsBlock(
            isRememberEnabled = screenModel.isRemember,
            isRememberClicked = screenModel::isRememberClicked,
            onForgotPasswordClicked = {
                screenModel.goResetPassword(navigator)
            },
        )
        HSpacer(24.dp)
        ButtonsBlock(
            onSignInClick = screenModel::signIn,
            onSkipSignInClick = screenModel::skipSignIn
        )
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun InputsBlock(
        loginField: TextFieldValue,
        passwordField: TextFieldValue,
        onLoginChanged: (TextFieldValue) -> Unit,
        onPasswordChanged: (TextFieldValue) -> Unit,
    ) {
        val (emailRef, passwordRef) = FocusRequester.createRefs()

        EmailInputField(
            modifier = Modifier.focusOrder(emailRef) {
                next = passwordRef
            },
            value = loginField,
            onValueChange = onLoginChanged,
        )
        HSpacer(8.dp)
        PasswordInputField(
            modifier = Modifier.focusOrder(passwordRef),
            label = "Password",
            value = passwordField,
            onValueChange = onPasswordChanged,
        )

        LaunchedEffect(Unit) {
            emailRef.requestFocus()
        }
    }

    @Composable
    private fun ButtonsBlock(
        onSignInClick: () -> Unit,
        onSkipSignInClick: () -> Unit,
    ) {
        AAMainButton(
            text = "Sign in",
            onClick = onSignInClick,
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
            onClick = onSkipSignInClick,
        )
    }

    @Composable
    private fun ActionsBlock(
        isRememberEnabled: Boolean,
        isRememberClicked: (Boolean) -> Unit,
        onForgotPasswordClicked: () -> Unit,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AACheckBoxWithText(
                checked = isRememberEnabled,
                onCheckedChange = isRememberClicked,
                text = "Remember me",
            )
            ClickableText(
                text = AnnotatedString("Forgot password?"),
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
                onClick = {
                    onForgotPasswordClicked()
                }
            )
        }
    }
}
