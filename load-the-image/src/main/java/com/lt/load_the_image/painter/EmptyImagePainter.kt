package com.lt.load_the_image.painter

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter

/**
 * creator: lt  2022/6/1  lt.dygzs@qq.com
 * effect : Empty image painter
 * warning:
 */
class EmptyImagePainter : Painter() {
    override val intrinsicSize: Size
        get() = Size(1f, 1f)

    override fun DrawScope.onDraw() {
    }
}