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

package com.lt.load_the_image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import com.lt.load_the_image.loader.DataToBeLoaded
import com.lt.load_the_image.loader.rememberDataToBeLoaded
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image
import java.io.File
import java.io.InputStream

/**
 * Load the image to @Composable Image
 * [url]Can use Net url or native url
 * [placeholderResource]Bitmap when loading network pictures (resource)
 */
@Composable
fun rememberImagePainter(url: String, placeholderResource: String = ""): Painter {
    val data = remember(url, placeholderResource) {
        val data = DataToBeLoaded(url)
        data.placeholderResource = placeholderResource
        data
    }
    return rememberImagePainter(data)
}

/**
 * Load the image to @Composable Image
 * [file]Local file
 */
@Composable
fun rememberImagePainter(file: File): Painter {
    return LoadTheImageManager.load(rememberDataToBeLoaded(file))
}

/**
 * Load the image to @Composable Image
 * [bitmap]The [Bitmap]
 */
@Composable
fun rememberImagePainter(bitmap: Bitmap): Painter {
    return LoadTheImageManager.load(rememberDataToBeLoaded(bitmap))
}

/**
 * Load the image to @Composable Image
 * [image]The [Image]
 */
@Composable
fun rememberImagePainter(image: Image): Painter {
    return LoadTheImageManager.load(rememberDataToBeLoaded(image))
}

/**
 * Load the image to @Composable Image
 * [byteArray]Bytes of image
 */
@Composable
fun rememberImagePainter(byteArray: ByteArray): Painter {
    return LoadTheImageManager.load(rememberDataToBeLoaded(byteArray))
}

/**
 * Load the image to @Composable Image
 * [inputStream]InputStream of image
 * [placeholderResource]Bitmap when loading network pictures (resource)
 * [isAutoCloseStream]Whether to close the stream automatically
 */
@Composable
fun rememberImagePainter(
    inputStream: InputStream,
    placeholderResource: String = "",
    isAutoCloseStream: Boolean = true
): Painter {
    val data = remember(inputStream, placeholderResource, isAutoCloseStream) {
        val data = DataToBeLoaded(inputStream)
        data.placeholderResource = placeholderResource
        data.isAutoCloseStream = isAutoCloseStream
        data
    }
    return rememberImagePainter(data)
}

/**
 * Load the image to @Composable Image
 * [data]Self assembling data
 */
@Composable
fun rememberImagePainter(data: DataToBeLoaded): Painter {
    return LoadTheImageManager.load(data)
}