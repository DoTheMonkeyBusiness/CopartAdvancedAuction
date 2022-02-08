package com.cprt.advancedauction.foundation.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.IconOpacity
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun AAOutlinedTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        interactionSource = interactionSource,
        colors = outlinedTextFieldColors()
    )
}

@Composable
private fun outlinedTextFieldColors(): TextFieldColors {
    val textColor = LocalContentColor.current.copy(LocalContentAlpha.current)
    val disabledTextColor = textColor.copy(ContentAlpha.disabled)
    val backgroundColor = Color.Transparent
    val cursorColor = MaterialTheme.colorScheme.primary
    val errorCursorColor = MaterialTheme.colorScheme.error
    val focusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high)
    val unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
    val disabledBorderColor = unfocusedBorderColor.copy(alpha = ContentAlpha.disabled)
    val errorBorderColor = MaterialTheme.colorScheme.error
    val leadingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = IconOpacity)
    val disabledLeadingIconColor = leadingIconColor.copy(alpha = ContentAlpha.disabled)
    val errorLeadingIconColor = leadingIconColor
    val trailingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = IconOpacity)
    val disabledTrailingIconColor = trailingIconColor.copy(alpha = ContentAlpha.disabled)
    val errorTrailingIconColor = MaterialTheme.colorScheme.error
    val focusedLabelColor = MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high)
    val unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(ContentAlpha.medium)
    val disabledLabelColor = unfocusedLabelColor.copy(ContentAlpha.disabled)
    val errorLabelColor = MaterialTheme.colorScheme.error
    val placeholderColor = MaterialTheme.colorScheme.onSurface.copy(ContentAlpha.medium)
    val disabledPlaceholderColor = placeholderColor.copy(ContentAlpha.disabled)

    return TextFieldDefaults.outlinedTextFieldColors(
        textColor = textColor,
        disabledTextColor = disabledTextColor,
        backgroundColor = backgroundColor,
        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        disabledBorderColor = disabledBorderColor,
        errorBorderColor = errorBorderColor,
        leadingIconColor = leadingIconColor,
        disabledLeadingIconColor = disabledLeadingIconColor,
        errorLeadingIconColor = errorLeadingIconColor,
        trailingIconColor = trailingIconColor,
        disabledTrailingIconColor = disabledTrailingIconColor,
        errorTrailingIconColor = errorTrailingIconColor,
        focusedLabelColor = focusedLabelColor,
        unfocusedLabelColor = unfocusedLabelColor,
        disabledLabelColor = disabledLabelColor,
        errorLabelColor = errorLabelColor,
        placeholderColor = placeholderColor,
        disabledPlaceholderColor = disabledPlaceholderColor,
    )
}
