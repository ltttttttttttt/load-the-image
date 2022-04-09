package com.lt.load_the_image.loader

import androidx.compose.ui.graphics.painter.Painter
import com.lt.load_the_image.LoadTheImageManager

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Default load the image
 * warning:
 */
open class DefaultLoadTheImage : LoadTheImage {
    override fun load(url: String): Painter {
        // TODO by lt 2022/4/8 18:23 需要处理线程切换和默认占位对象和占位图等的处理,异常和获取不到图片等情况的处理
        val byteArray =
            LoadTheImageManager.memoryCache.getCache(url)
                ?: LoadTheImageManager.fileCache.getCache(url)
                ?: LoadTheImageManager.httpLoader.load(url)
        byteArray?.let {
            LoadTheImageManager.memoryCache.saveCache(url, it)
            LoadTheImageManager.fileCache.saveCache(url, it)
        }
        return LoadTheImageManager.painterCreator.create(byteArray!!)
    }
}