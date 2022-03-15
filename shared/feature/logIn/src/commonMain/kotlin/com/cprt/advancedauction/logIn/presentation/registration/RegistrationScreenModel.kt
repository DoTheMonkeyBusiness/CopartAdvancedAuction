package com.cprt.advancedauction.logIn.presentation.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.auth.domain.model.UserCredentialsModel
import com.cprt.advancedauction.core.screen.internalNotification.InternalNotificationManager
import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.core.screen.screenModel.AAScreenModel
import com.cprt.advancedauction.core.screen.tools.LogInScreen
import com.cprt.advancedauction.core.screen.tools.ScreenProvider
import com.cprt.advancedauction.core.screen.useCase.ResultOf
import com.cprt.advancedauction.logIn.domain.useCase.SignUpUseCase
import com.cprt.advancedauction.logIn.utils.getErrorMessage
import kotlinx.coroutines.launch

private const val MINIMAL_PASSWORD_LENGTH = 6

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

        val emailText = loginField.text
        val passwordText = passwordField.text
        val repeatPasswordText = repeatPasswordField.text
        val isEmailEmpty = emailText.isEmpty()
        val isPasswordEmpty = passwordText.isEmpty()
        val isRepeatPasswordEmpty = repeatPasswordText.isEmpty()

        when {
            isEmailEmpty || isPasswordEmpty || isRepeatPasswordEmpty -> {
                processEmptyFields(
                    isEmailEmpty = isEmailEmpty,
                    isPasswordEmpty = isPasswordEmpty,
                )
            }
            isPasswordWeak(passwordText) -> {
                processWeakPassword()
            }
            passwordText != repeatPasswordText -> {
                processPasswordsDontMatch()
            }
            else -> {
                processSignUp(
                    email = emailText,
                    password = passwordText,
                )
            }
        }
    }

    private fun processSignUp(
        email: String,
        password: String
    ) {
        state = State.RegistrationProgress
        coroutineScope.launch {
            val credentialsModel = UserCredentialsModel(
                email = email,
                password = password,
            )

            state = when (val value = signUpUseCase(credentialsModel)) {
                is ResultOf.Success -> State.RegistrationSuccess
                is ResultOf.Failure -> State.RegistrationError(
                    message = value
                        .throwable
                        .getErrorMessage(loginErrorString.unspecified)
                )
            }
        }
    }

    private fun isPasswordWeak(password: String): Boolean {
        return password.length < MINIMAL_PASSWORD_LENGTH
    }

    private fun processEmptyFields(
        isEmailEmpty: Boolean,
        isPasswordEmpty: Boolean,
    ) {
        val errorMessage = when {
            isEmailEmpty -> loginErrorString.emptyEmailField
            isPasswordEmpty -> loginErrorString.emptyPasswordField
            else -> loginErrorString.emptyRepeatPasswordField
        }

        state = State.RegistrationError(errorMessage)
    }

    private fun processWeakPassword() {
        state = State.RegistrationError(loginErrorString.weakPassword)
    }

    private fun processPasswordsDontMatch() {
        state = State.RegistrationError(loginErrorString.passwordsDontMatch)
    }

    sealed class State {
        object Idle : State()
        object RegistrationProgress : State()
        object RegistrationSuccess : State()
        data class RegistrationError(val message: String) : State()
    }
}
