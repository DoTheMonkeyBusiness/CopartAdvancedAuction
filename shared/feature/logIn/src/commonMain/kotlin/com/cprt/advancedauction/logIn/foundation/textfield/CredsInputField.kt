package com.cprt.advancedauction.logIn.foundation.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.cprt.advancedauction.foundation.textfield.AAOutlinedTextField

@Composable
internal fun CredsInputField(
    modifier: Modifier = Modifier,
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    isPassword: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    AAOutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(
                text = label,
            )
        },
        singleLine = true,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
    )
}
