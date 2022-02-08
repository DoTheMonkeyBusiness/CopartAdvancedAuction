package com.cprt.advancedauction.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screen.tools.SplashScreen
import com.cprt.advancedauction.theme.icons.AAIcons
import com.cprt.advancedauction.theme.icons.aaicons.IcCopartLogo

class SplashScreenUI : SplashScreen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Surface {
            Button(
                onClick = {
                    navigator.pop()
                }
            ) {
                Text("pop")
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier.size(160.dp),
                    contentDescription = null,
                    imageVector = AAIcons.IcCopartLogo,
                )
            }
        }
    }
}
