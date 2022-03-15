package com.cprt.advancedauction.logIn.presentation.login

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
import com.cprt.advancedauction.core.screen.tools.ScreenProvider
import com.cprt.advancedauction.core.screen.useCase.ResultOf
import com.cprt.advancedauction.logIn.domain.model.SignInModel
import com.cprt.advancedauction.logIn.domain.useCase.SignInUseCase
import com.cprt.advancedauction.logIn.domain.useCase.SkipSignInUseCase
import com.cprt.advancedauction.logIn.presentation.registration.RegistrationScreenUI
import com.cprt.advancedauction.logIn.presentation.reset.ResetPasswordScreenUI
import com.cprt.advancedauction.logIn.utils.getErrorMessage
import kotlinx.coroutines.launch

internal class LoginScreenModel(
    private val internalNotificationManager: InternalNotificationManager,
    private val loginErrorString: LoginErrorString,
    private val screenProvider: ScreenProvider,
    private val signInUseCase: SignInUseCase,
    private val skipSignInUseCase: SkipSignInUseCase,
) : AAScreenModel<LoginScreenModel.State>(State.Idle) {

    var loginField by mutableStateOf(TextFieldValue())
        private set

    var passwordField by mutableStateOf(TextFieldValue())
        private set

    var isRemember by mutableStateOf(true)
        private set

    fun emailInputChanged(value: TextFieldValue) {
        loginField = value
    }

    fun passwordInputChanged(value: TextFieldValue) {
        passwordField = value
    }

    fun isRememberClicked(isRemember: Boolean) {
        this.isRemember = isRemember
    }

    fun goRegistration(navigator: Navigator) {
        state = State.Idle
        navigator.push(RegistrationScreenUI())
    }

    fun goResetPassword(navigator: Navigator) {
        state = State.Idle
        navigator.push(ResetPasswordScreenUI())
    }

    fun goHome(navigator: Navigator) {
        state = State.Idle
        navigator.replaceAll(screenProvider.mainScreen)
    }

    fun signIn() {
        if (state != State.Idle) return

        val emailText = loginField.text
        val passwordText = passwordField.text
        val isEmailEmpty = emailText.isEmpty()
        val isPasswordEmpty = passwordText.isEmpty()

        if (isEmailEmpty || isPasswordEmpty) {
            processEmptyFields(
                isEmailEmpty = isEmailEmpty,
            )
        } else {
            processSignIn(
                email = emailText,
                password = passwordText,
                isRememberUser = isRemember,
            )
        }
    }

    fun skipSignIn() {
        if (state != State.Idle) return
        state = State.SignInProgress

        coroutineScope.launch {
            state = when (val value = skipSignInUseCase(Unit)) {
                is ResultOf.Success -> State.SignInSuccess
                is ResultOf.Failure -> State.SignInError(
                    message = value
                        .throwable
                        .getErrorMessage(loginErrorString.unspecified)
                )
            }
        }
    }

    fun showNotification(text: String) {
        state = State.Idle
        internalNotificationManager.show(text)
    }

    private fun processSignIn(
        email: String,
        password: String,
        isRememberUser: Boolean,
    ) {
        state = State.SignInProgress
        coroutineScope.launch {
            val signInModel = SignInModel(
                userCredentials = UserCredentialsModel(
                    email = email,
                    password = password,
                ),
                isRemember = isRememberUser,
            )

            state = when (val value = signInUseCase(signInModel)) {
                is ResultOf.Success -> State.SignInSuccess
                is ResultOf.Failure -> State.SignInError(
                    message = value
                        .throwable
                        .getErrorMessage(loginErrorString.unspecified)
                )
            }
        }
    }

    private fun processEmptyFields(
        isEmailEmpty: Boolean,
    ) {
        val errorMessage = if (isEmailEmpty) {
            loginErrorString.emptyEmailField
        } else {
            loginErrorString.emptyPasswordField
        }

        state = State.SignInError(errorMessage)
    }

    sealed class State {
        object Idle : State()
        object SignInProgress : State()
        object SignInSuccess : State()
        data class SignInError(val message: String) : State()
    }
}
