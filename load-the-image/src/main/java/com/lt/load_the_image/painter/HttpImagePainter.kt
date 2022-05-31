package com.lt.load_the_image.painter

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter

/**
 * creator: lt  2022/5/31  lt.dygzs@qq.com
 * effect : Image painter with HTTP.
 * warning:
 */
class HttpImagePainter(
    imageBitmap: ImageBitmap? = null
) : Painter() {
    val imageBitmap: MutableState<ImageBitmap?> = mutableStateOf(imageBitmap)

    override val intrinsicSize: Size
        get() {
            val bitmap = imageBitmap.value ?: return Size(1f, 1f)
            return Size(bitmap.width.toFloat(), bitmap.height.toFloat())
        }

    override fun DrawScope.onDraw() {
        val bitmap = imageBitmap.value ?: return
        drawImage(bitmap)
    }
}