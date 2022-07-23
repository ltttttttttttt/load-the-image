package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import com.lt.load_the_image.LoadTheImageManager

/**
 * creator: lt  2022/6/1  lt.dygzs@qq.com
 * effect : Load the image from [ByteArray] of image
 * warning:
 */
class ByteArrayLoadTheImage : LoadTheImage {
    @Composable
    override fun load(data: DataToBeLoaded): Painter? {
        val byteArray = data.data as? ByteArray ?: return null
        return remember(byteArray) { LoadTheImageManager.painterCreator.create(byteArray) }
    }

    override fun canLoad(data: DataToBeLoaded): Boolean {
        return data.data is ByteArray
    }
}