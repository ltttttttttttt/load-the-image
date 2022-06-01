package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

/**
 * creator: lt  2022/6/1  lt.dygzs@qq.com
 * effect : Load the image from [Image]
 * warning:
 */
class ImageLoadTheImage : LoadTheImage {
    @Composable
    override fun load(data: DataToBeLoaded): Painter? {
        val image = data.data as? Image ?: return null
        return BitmapPainter(image.toComposeImageBitmap())
    }

    override fun canLoad(data: DataToBeLoaded): Boolean {
        return data.data is Image
    }
}