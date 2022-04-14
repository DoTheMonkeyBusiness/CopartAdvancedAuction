package com.cprt.advancedauction.logIn.presentation.resetPassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.core.internalNotification.InternalNotificationManager
import com.cprt.advancedauction.core.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.screenModel.AAScreenModel
import com.cprt.advancedauction.navigation.tools.ScreenProvider
import com.cprt.advancedauction.core.useCase.ResultOf
import com.cprt.advancedauction.logIn.domain.useCase.SendResetEmailUseCase
import com.cprt.advancedauction.logIn.utils.getLoginErrorMessage
import com.cprt.advancedauction.navigation.util.replaceIfPossible
import kotlinx.coroutines.launch

internal class ForgotPasswordScreenModel(
    private val internalNotificationManager: InternalNotificationManager,
    private val loginErrorString: LoginErrorString,
    private val screenProvider: ScreenProvider,
    private val sendResetCodeUseCase: SendResetEmailUseCase,
) : AAScreenModel<ForgotPasswordScreenModel.State>(State.Idle) {

    var emailField by mutableStateOf(TextFieldValue())
        private set

    fun emailInputChanged(value: TextFieldValue) {
        emailField = value
    }

    fun showNotification(text: String) {
        state = State.Idle
        internalNotificationManager.show(text)
    }

    fun goLogin(navigator: Navigator) {
        state = State.Idle
        navigator.replaceIfPossible(screenProvider.logInScreen)
    }

    fun onSendEmail() {
        if (state != State.Idle) return

        sendEmail(
            emailText = emailField.text,
        )
    }

    private fun sendEmail(
        emailText: String
    ) {
        state = State.SendEmailProgress
        coroutineScope.launch {
            state = when (val value = sendResetCodeUseCase(emailText)) {
                is ResultOf.Success -> State.SendEmailSuccess
                is ResultOf.Failure -> State.SendEmailError(
                    message = value
                        .throwable
                        .getLoginErrorMessage(loginErrorString.unspecified)
                )
            }
        }
    }

    sealed class State {
        object Idle : State()
        object SendEmailProgress : State()
        object SendEmailSuccess : State()
        data class SendEmailError(val message: String) : State()
    }
}
