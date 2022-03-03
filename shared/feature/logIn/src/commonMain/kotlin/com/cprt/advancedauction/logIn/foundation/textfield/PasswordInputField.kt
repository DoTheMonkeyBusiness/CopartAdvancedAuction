package com.cprt.advancedauction.logIn.foundation.textfield

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
internal fun PasswordInputField(
    modifier: Modifier = Modifier,
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    var isPassword by remember { mutableStateOf(true) }

    CredsInputField(
        modifier = modifier,
        label = label,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Password,
                contentDescription = null,
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    isPassword = !isPassword
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Visibility,
                    contentDescription = null,
                )
            }
        },
        isPassword = isPassword
    )
}
