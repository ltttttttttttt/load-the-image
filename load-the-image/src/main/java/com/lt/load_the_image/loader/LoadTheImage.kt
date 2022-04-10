package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Load the image: url to Painter
 * warning:
 */
fun interface LoadTheImage {

    /**
     * Complete process with load image
     * return null means it cannot be processed
     */
    @Composable
    fun load(url: String): Painter?
}