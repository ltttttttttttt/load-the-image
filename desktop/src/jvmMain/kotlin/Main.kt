import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.lt.common.App
import com.lt.load_the_image.LoadTheImageManager
import com.lt.load_the_image.rememberImagePainter
import java.io.File


fun main() {
    LoadTheImageManager.defaultErrorImagePath = "drawable-xxhdpi/load_error.jpeg"
    application {
        Window(onCloseRequest = ::exitApplication) {
            MaterialTheme {
                //TestLazyList()
                UI()
            }
        }
    }
}

@Composable
private fun UI() {
    Column {
        Row {
            Image(
                rememberImagePainter(
                    "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/e850352ac65c10380e49077eba119313b07e8953.jpg",
                    "drawable-xxhdpi/img_a.jpeg"
                ), "", Modifier.size(50.dp)
            )
            Image(
                rememberImagePainter(
                    File("C:\\SpringBootFiles\\imgs\\7633948650d6b30461fce6d13422ec3a.jpeg"),
                ), "", Modifier.size(50.dp)
            )
            Image(
                rememberImagePainter(
                    remember { File("C:\\SpringBootFiles\\imgs\\7633948650d6b30461fce6d13422ec3a.jpeg").inputStream() },
                ), "", Modifier.size(50.dp)
            )
        }
        App()
    }
}