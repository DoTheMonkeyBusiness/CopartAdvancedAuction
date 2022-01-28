package com.cprt.advancedauction.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screen.tools.ScreenProvider
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import androidx.compose.material.Text

class TemporaryScreen : Screen, KoinComponent {

    private val screenProvider by inject<ScreenProvider>()

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val buttons = remember(navigator) {
            getButtons(navigator, screenProvider)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            buttons.forEach {
                Button(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        end = 24.dp,
                    ),
                    onClick = it.onClick
                ) {
                    Text(it.text)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    private fun getButtons(
        navigator: Navigator,
        screenProvider: ScreenProvider,
    ): List<TempButtonModel> {
        return listOf(
            TempButtonModel(
                text = "logIn",
                onClick = { navigator.push(screenProvider.logInScreen) }
            ),
            TempButtonModel(
                text = "main",
                onClick = { navigator.push(screenProvider.mainScreen) }
            ),
            TempButtonModel(
                text = "onBoarding",
                onClick = { navigator.push(screenProvider.onBoardingScreen) }
            ),
            TempButtonModel(
                text = "splash",
                onClick = { navigator.push(screenProvider.splashScreen) }
            )
        )
    }

    private data class TempButtonModel(
        val text: String,
        val onClick: () -> Unit,
    )
}
