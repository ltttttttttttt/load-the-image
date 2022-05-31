import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.lt.common.App
import com.lt.load_the_image.LoadTheImageManager

fun main() {
    LoadTheImageManager.defaultErrorImagePath = "drawable-xxhdpi/load_error.jpeg"
    application {
        Window(onCloseRequest = ::exitApplication) {
            MaterialTheme {
                App()
            }
        }
    }
}