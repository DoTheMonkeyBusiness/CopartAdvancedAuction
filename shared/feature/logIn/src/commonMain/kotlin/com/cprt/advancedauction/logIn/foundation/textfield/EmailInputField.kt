package com.cprt.advancedauction.logIn.foundation.textfield

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
internal fun EmailInputField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    CredsInputField(
        modifier = modifier,
        label = "Email",
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = null,
            )
        }
    )
}