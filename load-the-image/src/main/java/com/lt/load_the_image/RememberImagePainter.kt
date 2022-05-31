package com.lt.load_the_image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import com.lt.load_the_image.painter.HttpImagePainter

/**
 * Load the image to @Composable Image
 * [url]Can use Net url or native url
 */
@Composable
fun rememberImagePainter(url: String): Painter {
    return LoadTheImageManager.load(url)
}

/**
 * Load the image to @Composable Image
 * [url]Can use Net url or native url
 * [placeholderResource]Bitmap when loading network pictures (resource)
 */
@Composable
fun rememberImagePainter(url: String, placeholderResource: String): Painter {
    val painter = rememberImagePainter(url)
    remember(url) {
        if (painter is HttpImagePainter && painter.imageBitmap.value == null)
            painter.imageBitmap.value =
                LoadTheImageManager.loadResourceImageBitmap(placeholderResource)
    }
    return painter
}