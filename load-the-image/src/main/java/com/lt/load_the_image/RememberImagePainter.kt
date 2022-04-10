package com.lt.load_the_image

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

/**
 * Load the image to @Composable Image
 * [url]Can use Net url or native url
 */
@Composable
fun rememberImagePainter(url: String): Painter {
    return LoadTheImageManager.load(url)
}