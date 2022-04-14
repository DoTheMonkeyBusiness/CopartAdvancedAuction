package com.cprt.advancedauction.navigation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.cprt.advancedauction.navigation.transition.AAScreenTransition
import com.cprt.advancedauction.navigation.util.replaceIfPossible
import com.cprt.advancedauction.recources.icons.AAIcons
import com.cprt.advancedauction.recources.icons.aaicons.IcCopartLogo

abstract class TwoPaneMainScreen : TwoPaneScreen() {

    @Composable
    final override fun Content() {
        Navigator(
            screen = MainPane(),
        ) { navigator ->
            if (isInTwoPaneMode) {
                TwoPaneContent(
                    navigator = navigator,
                )
            } else {
                OnePaneContent(
                    navigator = navigator,
                )
            }
        }
    }

    @Composable
    protected abstract fun MainPaneContent()

    @Composable
    protected open fun TobBarContent() = Unit

    @Composable
    private fun TwoPaneContent(
        modifier: Modifier = Modifier,
        navigator: Navigator
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
        ) {
            TobBarContent()
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                Surface(
                    modifier = Modifier.weight(1f)
                ) {
                    MainPaneContent()
                }
                Surface(
                    modifier = Modifier.weight(1.618f),
                ) {
                    if (navigator.lastItem is MainPane) {
                        SelectionPlaceholder()
                    } else {
                        CurrentScreen()
                    }
                }
            }
        }
    }

    @Composable
    private fun OnePaneContent(
        navigator: Navigator
    ) {
        AAScreenTransition(navigator = navigator) { screen ->
            screen.Content()
        }
    }

    @Composable
    private fun SelectionPlaceholder(
        modifier: Modifier = Modifier,
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.size(160.dp),
                contentDescription = null,
                imageVector = AAIcons.IcCopartLogo,
            )
        }
    }

    protected fun Navigator.changePane(screen: Screen) {
        if (lastItem is TwoPaneMainScreen.MainPane) push(screen) else replaceIfPossible(screen)
    }

    private inner class MainPane : Screen {
        @Composable
        override fun Content() {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                TobBarContent()
                MainPaneContent()
            }
        }
    }
}
