package com.cprt.advancedauction.logIn.foundation

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
internal fun CommonAdditionalBlock(
    modifier: Modifier = Modifier,
    description: String,
    action: String,
    onActionClick: () -> Unit,
) {
    val annotatedText = buildAnnotatedString {
        append(description)
        append(" ")

        pushStringAnnotation(
            tag = action,
            annotation = action
        )
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
            )
        ) {
            append(action)
        }
        pop()
    }
    ClickableText(
        modifier = modifier,
        text = annotatedText,
        style = MaterialTheme.typography.bodyMedium
            .copy(color = MaterialTheme.colorScheme.onSurface),
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = action,
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                onActionClick()
            }
        },
    )
}