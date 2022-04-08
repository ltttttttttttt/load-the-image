package com.lt.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

actual fun getPlatformName(): String {
    return "Desktop"
}

@Composable
actual fun rememberImagePainter(url: String): Painter =
    com.lt.load_the_image.rememberImagePainter(url)