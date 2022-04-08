package com.lt.load_the_image.painter

import androidx.compose.ui.graphics.painter.Painter

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Create [Painter] from ByteArray
 * warning:
 */
fun interface PainterCreator {

    /**
     * Create [Painter] from [byteArray]
     */
    fun create(byteArray: ByteArray): Painter
}