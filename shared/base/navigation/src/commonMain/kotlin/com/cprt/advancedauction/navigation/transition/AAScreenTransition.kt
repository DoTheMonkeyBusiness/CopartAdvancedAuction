package com.cprt.advancedauction.navigation.transition

import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScreenTransition
import cafe.adriel.voyager.transitions.ScreenTransitionContent

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AAScreenTransition(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    content: ScreenTransitionContent,
) {
    ScreenTransition(
        navigator = navigator,
        modifier = modifier,
        content = content,
        transition = {
            val lastEvent = navigator.lastEvent

            if (lastEvent == StackEvent.Replace) {
                scaleTransform()
            } else {
                slideTransform(
                    lastEvent = lastEvent,
                )
            }
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
private fun scaleTransform(): ContentTransform {
    val animationSpec: FiniteAnimationSpec<Float> = spring(stiffness = Spring.StiffnessMediumLow)
    return scaleIn(initialScale = 1.2f, animationSpec = animationSpec) with
            scaleOut(targetScale = 1f, animationSpec = animationSpec)
}

@OptIn(ExperimentalAnimationApi::class)
private fun slideTransform(
    lastEvent: StackEvent,
): ContentTransform {
    val animationSpec: FiniteAnimationSpec<IntOffset> = spring(
        stiffness = Spring.StiffnessMediumLow,
        visibilityThreshold = IntOffset.VisibilityThreshold
    )
    val (initialOffset, targetOffset) = when (lastEvent) {
        StackEvent.Pop -> ({ size: Int -> -size }) to ({ size: Int -> size })
        else -> ({ size: Int -> size }) to ({ size: Int -> -size })
    }

    return slideInHorizontally(animationSpec, initialOffset) with slideOutHorizontally(animationSpec, targetOffset)
}
