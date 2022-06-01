package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Load the image: url to Painter
 * warning:
 */
interface LoadTheImage {

    /**
     * Complete process with load image
     */
    @Composable
    fun load(data: DataToBeLoaded): Painter?

    /**
     * Return false means it cannot be processed
     */
    fun canLoad(data: DataToBeLoaded): Boolean
}

/**
 * Data loaded in [LoadTheImage]
 * [data]The data
 */
open class DataToBeLoaded(val data: Any) {
    /**
     * Bitmap when loading network pictures (resource)
     */
    var placeholderResource: String = ""

    /**
     * Whether to close the stream automatically when [data] is a stream
     */
    var isAutoCloseStream: Boolean = true
}