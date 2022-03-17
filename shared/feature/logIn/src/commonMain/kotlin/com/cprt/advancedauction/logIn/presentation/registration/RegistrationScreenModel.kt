package com.cprt.advancedauction.logIn.presentation.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.core.screen.internalNotification.InternalNotificationManager
import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.screen.screenModel.AAScreenModel
import com.cprt.advancedauction.core.screen.tools.LogInScreen
import com.cprt.advancedauction.core.screen.tools.ScreenProvider
import com.cprt.advancedauction.core.screen.useCase.ResultOf
import com.cprt.advancedauction.logIn.domain.model.SignUpModel
import com.cprt.advancedauction.logIn.domain.useCase.SignUpUseCase
import com.cprt.advancedauction.logIn.utils.getLoginErrorMessage
import kotlinx.coroutines.launch

internal class RegistrationScreenModel(
    private val internalNotificationManager: InternalNotificationManager,
    private val loginErrorString: LoginErrorString,
    private val signUpUseCase: SignUpUseCase,
    private val screenProvider: ScreenProvider,
) : AAScreenModel<RegistrationScreenModel.State>(State.Idle) {

    var loginField by mutableStateOf(TextFieldValue())
        private set

    var passwordField by mutableStateOf(TextFieldValue())
        private set

    var repeatPasswordField by mutableStateOf(TextFieldValue())
        private set

    fun emailInputChanged(value: TextFieldValue) {
        loginField = value
    }

    fun passwordInputChanged(value: TextFieldValue) {
        passwordField = value
    }

    fun repeatPasswordInputChanged(value: TextFieldValue) {
        repeatPasswordField = value
    }

    fun showNotification(text: String) {
        state = State.Idle
        internalNotificationManager.show(text)
    }

    fun goHome(navigator: Navigator) {
        state = State.Idle
        navigator.replaceAll(screenProvider.mainScreen)
    }

    fun goLogin(navigator: Navigator) {
        state = State.Idle
        if (navigator.items.any { it is LogInScreen }) {
            navigator.popUntil { it is LogInScreen }
        } else {
            navigator.replace(screenProvider.logInScreen)
        }
    }

    fun singUp() {
        if (state != State.Idle) return

        processSignUp(
            email = loginField.text,
            password = passwordField.text,
            repeatPasswordText = repeatPasswordField.text,
        )
    }

    private fun processSignUp(
        email: String,
        password: String,
        repeatPasswordText: String,
    ) {
        state = State.RegistrationProgress
        coroutineScope.launch {
            val signUpModel = SignUpModel(
                email = email,
                password = password,
                repeatedPassword = repeatPasswordText,
            )

            state = when (val value = signUpUseCase(signUpModel)) {
                is ResultOf.Success -> State.RegistrationSuccess
                is ResultOf.Failure -> State.RegistrationError(
                    message = value
                        .throwable
                        .getLoginErrorMessage(loginErrorString.unspecified)
                )
            }
        }
    }

    sealed class State {
        object Idle : State()
        object RegistrationProgress : State()
        object RegistrationSuccess : State()
        data class RegistrationError(val message: String) : State()
    }
}
