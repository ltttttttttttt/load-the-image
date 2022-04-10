package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.lt.load_the_image.LoadTheImageManager
import com.lt.load_the_image.util.println
import java.io.File

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Load the image from file path
 * warning:
 */
open class FileLoadTheImage : LoadTheImage {
    @Composable
    override fun load(url: String): Painter? {
        val file = File(url)
        val byteArray = LoadTheImageManager.memoryCache.getCache(url) ?: try {
            file.readBytes()
        } catch (e: Exception) {
            e.println()
            return null
        }
        LoadTheImageManager.memoryCache.saveCache(url, byteArray)
        return LoadTheImageManager.painterCreator.create(byteArray)
    }

    override fun canLoad(url: String): Boolean {
        val file = File(url)
        if (file.exists() && file.isFile)
            return true
        return false
    }
}