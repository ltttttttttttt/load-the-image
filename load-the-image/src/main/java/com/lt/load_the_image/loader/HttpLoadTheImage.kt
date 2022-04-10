package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.lt.load_the_image.LoadTheImageManager

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Load the image from network
 * warning:
 */
open class HttpLoadTheImage : LoadTheImage {
    @Composable
    override fun load(url: String): Painter? {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            return null
        val byteArray =
            LoadTheImageManager.memoryCache.getCache(url)
                ?: LoadTheImageManager.fileCache.getCache(url)
                ?: LoadTheImageManager.httpLoader.load(url)
                ?: return null
        LoadTheImageManager.memoryCache.saveCache(url, byteArray)
        LoadTheImageManager.fileCache.saveCache(url, byteArray)
        return LoadTheImageManager.painterCreator.create(byteArray)
    }
}