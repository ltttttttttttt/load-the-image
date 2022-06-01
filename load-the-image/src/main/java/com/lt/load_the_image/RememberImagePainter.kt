package com.lt.load_the_image

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.lt.load_the_image.loader.DataToBeLoaded
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
    val data = DataToBeLoaded(url)
    data.placeholderResource = placeholderResource
    return rememberImagePainter(data)
}

/**
 * Load the image to @Composable Image
 * [file]Local file
 */
@Composable
fun rememberImagePainter(file: File): Painter {
    return LoadTheImageManager.load(DataToBeLoaded(file))
}

/**
 * Load the image to @Composable Image
 * [bitmap]The [Bitmap]
 */
@Composable
fun rememberImagePainter(bitmap: Bitmap): Painter {
    return LoadTheImageManager.load(DataToBeLoaded(bitmap))
}

/**
 * Load the image to @Composable Image
 * [image]The [Image]
 */
@Composable
fun rememberImagePainter(image: Image): Painter {
    return LoadTheImageManager.load(DataToBeLoaded(image))
}

/**
 * Load the image to @Composable Image
 * [byteArray]Bytes of image
 */
@Composable
fun rememberImagePainter(byteArray: ByteArray): Painter {
    return LoadTheImageManager.load(DataToBeLoaded(byteArray))
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
    val data = DataToBeLoaded(inputStream)
    data.placeholderResource = placeholderResource
    data.isAutoCloseStream = isAutoCloseStream
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