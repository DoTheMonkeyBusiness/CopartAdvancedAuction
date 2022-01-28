import com.cprt.advancedauction.common.App
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.cprt.advancedauction.common.koin.initKoin

fun main() = application {
    initKoin()
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            App()
        }
    }
}