package com.lt.load_the_image.painter

import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image.Companion.makeFromEncoded


/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Default PainterCreator
 * warning:
 */
open class DefaultPainterCreator : PainterCreator {
    override fun create(byteArray: ByteArray): Painter {
        return BitmapPainter(makeFromEncoded(byteArray).toComposeImageBitmap())
    }
}