package com.cprt.advancedauction.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = colorLightPrimary,
    onPrimary = colorLightOnPrimary,
    primaryContainer = colorLightPrimaryContainer,
    onPrimaryContainer = colorLightOnPrimaryContainer,
    secondary = colorLightSecondary,
    onSecondary = colorLightOnSecondary,
    secondaryContainer = colorLightSecondaryContainer,
    onSecondaryContainer = colorLightOnSecondaryContainer,
    tertiary = colorLightTertiary,
    onTertiary = colorLightOnTertiary,
    tertiaryContainer = colorLightTertiaryContainer,
    onTertiaryContainer = colorLightOnTertiaryContainer,
    error = colorLightError,
    errorContainer = colorLightErrorContainer,
    onError = colorLightOnError,
    onErrorContainer = colorLightOnErrorContainer,
    background = colorLightBackground,
    onBackground = colorLightOnBackground,
    surface = colorLightSurface,
    onSurface = colorLightOnSurface,
    surfaceVariant = colorLightSurfaceVariant,
    onSurfaceVariant = colorLightOnSurfaceVariant,
    outline = colorLightOutline,
    inverseOnSurface = colorLightInverseOnSurface,
    inverseSurface = colorLightInverseSurface,
    inversePrimary = colorLightInversePrimary,
//    shadow = colorLightShadow,
)
private val DarkColorScheme = darkColorScheme(
    primary = colorDarkPrimary,
    onPrimary = colorDarkOnPrimary,
    primaryContainer = colorDarkPrimaryContainer,
    onPrimaryContainer = colorDarkOnPrimaryContainer,
    secondary = colorDarkSecondary,
    onSecondary = colorDarkOnSecondary,
    secondaryContainer = colorDarkSecondaryContainer,
    onSecondaryContainer = colorDarkOnSecondaryContainer,
    tertiary = colorDarkTertiary,
    onTertiary = colorDarkOnTertiary,
    tertiaryContainer = colorDarkTertiaryContainer,
    onTertiaryContainer = colorDarkOnTertiaryContainer,
    error = colorDarkError,
    errorContainer = colorDarkErrorContainer,
    onError = colorDarkOnError,
    onErrorContainer = colorDarkOnErrorContainer,
    background = colorDarkBackground,
    onBackground = colorDarkOnBackground,
    surface = colorDarkSurface,
    onSurface = colorDarkOnSurface,
    surfaceVariant = colorDarkSurfaceVariant,
    onSurfaceVariant = colorDarkOnSurfaceVariant,
    outline = colorDarkOutline,
    inverseOnSurface = colorDarkInverseOnSurface,
    inverseSurface = colorDarkInverseSurface,
    inversePrimary = colorDarkInversePrimary,
//    shadow = colorDarkShadow,
)

@Composable
internal expect fun AppSideEffect(
    isDarkTheme: Boolean,
    colorScheme: ColorScheme
)

@Composable
fun AppMaterialTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    AppSideEffect(
        isDarkTheme = isDarkTheme,
        colorScheme = colorScheme
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
