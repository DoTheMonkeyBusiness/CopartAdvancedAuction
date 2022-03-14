package com.cprt.advancedauction.internalNotification.foundation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cprt.advancedauction.core.screen.ScreenSize
import com.cprt.advancedauction.core.screen.tools.LocalScreenSize
import com.cprt.advancedauction.foundation.AACard
import com.cprt.advancedauction.internalNotification.domain.model.NotificationModel
import com.cprt.advancedauction.internalNotification.util.VisibilityHolder

private const val EXIT_ANIMATION_SLIDE = 320

@Composable
internal fun ColumnScope.NotificationUI(
    modifier: Modifier = Modifier,
    notificationData: Pair<NotificationModel, VisibilityHolder>,
    screenWidth: Dp,
    onDispose: () -> Unit,
) {
    val notificationModel = notificationData.first
    val visibilityHolder = notificationData.second
    val density = LocalDensity.current
    val (cardModifier, horizontalSlide) = getCardSpecificValue(
        modifier = modifier,
        screenWidth = screenWidth
    )

    key(notificationModel.id) {
        LaunchedEffect(Unit) {
            visibilityHolder.updateVisibility()
        }
        AnimatedVisibility(
            visible = visibilityHolder.isVisible,
            enter = slideInVertically(),
            exit = slideOutHorizontally {
                with(density) { horizontalSlide.roundToPx() }
            }
        ) {
            NotificationCard(
                modifier = cardModifier,
                text = notificationModel.text,
                onDispose = onDispose
            )
        }
    }
}

@Composable
private fun ColumnScope.NotificationCard(
    modifier: Modifier = Modifier,
    text: String,
    onDispose: () -> Unit,
) {
    AACard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colorScheme.tertiary,
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            text = text,
            style = MaterialTheme.typography.bodyMedium,
        )
        DisposableEffect(Unit) {
            onDispose(
                onDisposeEffect = onDispose
            )
        }
    }
}

@Composable
private fun getCardSpecificValue(
    modifier: Modifier = Modifier,
    screenWidth: Dp,
): Pair<Modifier, Dp> = if (LocalScreenSize.current == ScreenSize.Large) {
    modifier
        .width(EXIT_ANIMATION_SLIDE.dp)
        .padding(end = 8.dp) to EXIT_ANIMATION_SLIDE.dp
} else {
    modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp) to screenWidth
}
