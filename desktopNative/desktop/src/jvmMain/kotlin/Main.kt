import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.cprt.advancedauction.common.App
import com.cprt.advancedauction.common.koin.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Copart advanced auction",
        state = rememberWindowState(
            width = 1224.dp,
            height = 800.dp
        ),
    ) {
        App()
    }
}