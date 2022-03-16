package com.cprt.advancedauction.logIn.presentation.registration

import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screen.screenModel.getScreenModel
import com.cprt.advancedauction.core.screen.tools.RegistrationScreen
import com.cprt.advancedauction.foundation.button.AABackButton
import com.cprt.advancedauction.foundation.button.AAMainButton
import com.cprt.advancedauction.foundation.spacer.HSpacer
import com.cprt.advancedauction.logIn.foundation.CommonAdditionalBlock
import com.cprt.advancedauction.logIn.foundation.CommonHeaderBlock
import com.cprt.advancedauction.logIn.foundation.CommonWindow
import com.cprt.advancedauction.logIn.foundation.textfield.EmailInputField
import com.cprt.advancedauction.logIn.foundation.textfield.PasswordInputField

class RegistrationScreenUI : RegistrationScreen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<RegistrationScreenModel>()
        val state by screenModel.stateFlow.collectAsState()
        var isNeedToShowProgress: Boolean by remember { mutableStateOf(false) }

        when (val currentState = state) {
            is RegistrationScreenModel.State.Idle -> isNeedToShowProgress = false
            is RegistrationScreenModel.State.RegistrationProgress -> isNeedToShowProgress = true
            is RegistrationScreenModel.State.RegistrationSuccess -> screenModel.goHome(navigator)
            is RegistrationScreenModel.State.RegistrationError -> screenModel.showNotification(currentState.message)
        }

        Surface {
            CommonWindow(
                isNeedToShowProgress = isNeedToShowProgress,
                additionalContent = {
                    CommonAdditionalBlock(
                        description = "Already have an account?",
                        action = "Sign in",
                        onActionClick = {
                            screenModel.goLogin(navigator)
                        }
                    )
                },
                navigationIcon = {
                    AABackButton(
                        onClick = {
                            navigator.pop()
                        }
                    )
                },
                cardContent = {
                    RegistrationBlock(
                        screenModel = screenModel,
                    )
                },
            )
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun RegistrationBlock(
        screenModel: RegistrationScreenModel,
    ) {
        CommonHeaderBlock(
            title = "Create your Account",
            description = "Register to access user-specific features.",
        )
        HSpacer(16.dp)
        InputsBlock(
            loginField = screenModel.loginField,
            passwordField = screenModel.passwordField,
            repeatPasswordField = screenModel.repeatPasswordField,
            onLoginChanged = screenModel::emailInputChanged,
            onPasswordChanged = screenModel::passwordInputChanged,
            onRepeatPasswordChanged = screenModel::repeatPasswordInputChanged,
        )
        HSpacer(24.dp)
        AAMainButton(
            text = "Create my account",
            onClick = {
                screenModel.singUp()
            },
        )
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun InputsBlock(
        loginField: TextFieldValue,
        passwordField: TextFieldValue,
        repeatPasswordField: TextFieldValue,
        onLoginChanged: (TextFieldValue) -> Unit,
        onPasswordChanged: (TextFieldValue) -> Unit,
        onRepeatPasswordChanged: (TextFieldValue) -> Unit,
    ) {
        val (emailRef, passwordRef, repeatPasswordRef) = FocusRequester.createRefs()

        EmailInputField(
            modifier = Modifier.focusOrder(emailRef) {
                next = passwordRef
            },
            value = loginField,
            onValueChange = onLoginChanged,
        )
        HSpacer(8.dp)
        PasswordInputField(
            modifier = Modifier.focusOrder(passwordRef) {
                next = repeatPasswordRef
            },
            label = "Password",
            value = passwordField,
            onValueChange = onPasswordChanged,
        )
        HSpacer(8.dp)
        PasswordInputField(
            modifier = Modifier.focusOrder(repeatPasswordRef),
            label = "Repeat password",
            value = repeatPasswordField,
            onValueChange = onRepeatPasswordChanged,
        )

        LaunchedEffect(Unit) {
            emailRef.requestFocus()
        }
    }
}
