/*
 * Copyright lt 2023
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.foundation.Image
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.lt.common.App
import com.lt.load_the_image.LoadTheImageManager
import com.lt.load_the_image.loader.DataToBeLoaded
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
                    remember {
                        DataToBeLoaded(
                            try {
                                File("C:\\SpringBootFiles\\imgs\\7633948650d6b30461fce6d13422ec3a.jpeg").inputStream()
                            } catch (e: Exception) {
                                File("C:\\SpringBootFiles\\imgs\\7633948650d6b30461fce6d13422ec3a.jpeg")
                            }
                        )
                    },
                ), "", Modifier.size(50.dp)
            )
        }
        Box(Modifier.fillMaxSize()) {
            val lazyColumnState: LazyListState = rememberLazyListState()
            App(lazyColumnState)
            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                adapter = rememberScrollbarAdapter(
                    scrollState = lazyColumnState
                )
            )
        }
    }
}