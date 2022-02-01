import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.cprt.advancedauction.common.App
import com.cprt.advancedauction.common.koin.initKoin

fun main() = application {
    initKoin()
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}