package com.cprt.advancedauction.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screenModel.getScreenModel
import com.cprt.advancedauction.core.tools.SplashScreen
import com.cprt.advancedauction.theme.icons.AAIcons
import com.cprt.advancedauction.theme.icons.aaicons.IcCopartLogo

class SplashScreenUI : SplashScreen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<SplashScreenModel>()

        val state by screenModel.stateFlow.collectAsState()
        when (state) {
            is SplashScreenModel.State.Initial -> screenModel.onInit()
            is SplashScreenModel.State.Idle -> Unit
            is SplashScreenModel.State.GoHome -> screenModel.goHome(navigator)
            is SplashScreenModel.State.GoLogin -> screenModel.goLogin(navigator)
        }
        Surface {
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
