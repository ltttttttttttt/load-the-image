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
                //Image(com.lt.load_the_image.rememberImagePainter("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/e850352ac65c10380e49077eba119313b07e8953.jpg","drawable-xxhdpi/img_a.jpeg"), "", Modifier.size(500.dp))
            }
        }
    }
}