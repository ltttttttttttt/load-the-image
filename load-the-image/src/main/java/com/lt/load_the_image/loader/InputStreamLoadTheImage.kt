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

package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadImageBitmap
import com.lt.load_the_image.LoadTheImageManager
import com.lt.load_the_image.painter.AsyncImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

/**
 * creator: lt  2022/6/1  lt.dygzs@qq.com
 * effect : Load the image from [InputStream] of image
 * warning:
 */
class InputStreamLoadTheImage : LoadTheImage {
    @Composable
    override fun load(data: DataToBeLoaded): Painter? {
        val inputStream = data.data as? InputStream ?: return null
        val painter = remember(inputStream) { AsyncImagePainter() }
        LaunchedEffect(inputStream) {
            withContext(Dispatchers.IO) {
                val placeholderResource = data.placeholderResource
                if (placeholderResource.isNotEmpty())
                    painter.imageBitmap.value =
                        LoadTheImageManager.loadResourceImageBitmap(placeholderResource)
                val imageBitmap = loadImageBitmap(inputStream)
                if (data.isAutoCloseStream)
                    try {
                        inputStream.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                painter.imageBitmap.value = imageBitmap
            }
        }
        return painter
    }

    override fun canLoad(data: DataToBeLoaded): Boolean {
        return data.data is InputStream
    }
}