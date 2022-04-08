package com.lt.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

actual fun getPlatformName(): String {
    return "Android"
}

@Composable
actual fun rememberImagePainter(url: String): Painter =
    coil.compose.rememberImagePainter(data = url)