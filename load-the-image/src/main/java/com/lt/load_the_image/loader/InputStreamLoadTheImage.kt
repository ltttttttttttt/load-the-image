package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadImageBitmap
import com.lt.load_the_image.LoadTheImageManager
import com.lt.load_the_image.painter.AsyncImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

/**
 * creator: lt  2022/6/1  lt.dygzs@qq.com
 * effect : Load the image from [InputStream] of image
 * warning:
 */
class InputStreamLoadTheImage : LoadTheImage {
    @Composable
    override fun load(data: DataToBeLoaded): Painter? {
        val inputStream = data.data as? InputStream ?: return null
        val painter = AsyncImagePainter()
        LaunchedEffect(inputStream) {
            withContext(Dispatchers.IO) {
                val placeholderResource = data.placeholderResource
                if (placeholderResource.isNotEmpty())
                    painter.imageBitmap.value =
                        LoadTheImageManager.loadResourceImageBitmap(placeholderResource)
                val imageBitmap = loadImageBitmap(inputStream)
                if (data.isAutoCloseStream)
                    try {
                        inputStream.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                painter.imageBitmap.value = imageBitmap
            }
        }
        return painter
    }

    override fun canLoad(data: DataToBeLoaded): Boolean {
        return data.data is InputStream
    }
}