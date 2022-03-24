package com.cprt.advancedauction.logIn.presentation.resetPassword

import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screenModel.getScreenModel
import com.cprt.advancedauction.core.tools.ForgotPasswordScreen
import com.cprt.advancedauction.foundation.button.AABackButton
import com.cprt.advancedauction.foundation.button.AAMainButton
import com.cprt.advancedauction.foundation.spacer.HSpacer
import com.cprt.advancedauction.logIn.foundation.CommonHeaderBlock
import com.cprt.advancedauction.logIn.foundation.CommonWindow
import com.cprt.advancedauction.logIn.foundation.textfield.EmailInputField

class ForgotPasswordScreenUI : ForgotPasswordScreen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<ForgotPasswordScreenModel>()
        val state by screenModel.stateFlow.collectAsState()
        var isNeedToShowProgress: Boolean by remember { mutableStateOf(false) }

        when (val currentState = state) {
            is ForgotPasswordScreenModel.State.Idle -> isNeedToShowProgress = false
            is ForgotPasswordScreenModel.State.SendEmailProgress -> isNeedToShowProgress = true
            is ForgotPasswordScreenModel.State.SendEmailSuccess -> screenModel.goLogin(navigator)
            is ForgotPasswordScreenModel.State.SendEmailError -> screenModel.showNotification(currentState.message)
        }

        Surface {
            CommonWindow(
                isNeedToShowProgress = isNeedToShowProgress,
                navigationIcon = {
                    AABackButton(
                        onClick = {
                            navigator.pop()
                        }
                    )
                }
            ) {
                ResetPasswordBlock(
                    screenModel = screenModel,
                )
            }
        }
    }

    @Composable
    private fun ResetPasswordBlock(
        screenModel: ForgotPasswordScreenModel
    ) {
        val focusRequester = remember { FocusRequester() }

        CommonHeaderBlock(
            title = "Forgot password",
            description = "Please enter your registration email address. We'll send instructions to help reset your password.",
        )
        HSpacer(16.dp)
        EmailInputField(
            modifier = Modifier.focusRequester(focusRequester),
            value = screenModel.emailField,
            onValueChange = screenModel::emailInputChanged,
        )
        HSpacer(24.dp)
        AAMainButton(
            text = "Send reset instructions",
            onClick = screenModel::onSendEmail,
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}
