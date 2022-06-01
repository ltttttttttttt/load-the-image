package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asComposeImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.skia.Bitmap

/**
 * creator: lt  2022/6/1  lt.dygzs@qq.com
 * effect : Load the image from [Bitmap]
 * warning:
 */
class BitmapLoadTheImage : LoadTheImage {
    @Composable
    override fun load(data: DataToBeLoaded): Painter? {
        val bitmap = data.data as? Bitmap ?: return null
        return BitmapPainter(bitmap.asComposeImageBitmap())
    }

    override fun canLoad(data: DataToBeLoaded): Boolean {
        return data.data is Bitmap
    }
}