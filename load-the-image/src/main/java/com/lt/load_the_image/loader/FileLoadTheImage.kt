package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    override fun load(data: DataToBeLoaded): Painter? {
        val file = if (data.data is String)
            remember { File(data.data) }
        else if (data.data is File)
            data.data
        else
            return null
        val painter = remember(file.absolutePath) {
            val byteArray = LoadTheImageManager.memoryCache.getCache(file.absolutePath) ?: try {
                file.readBytes()
            } catch (e: Exception) {
                e.println()
                return null
            }
            LoadTheImageManager.memoryCache.saveCache(file.absolutePath, byteArray)
            LoadTheImageManager.painterCreator.create(byteArray)
        }
        return painter
    }

    override fun canLoad(data: DataToBeLoaded): Boolean {
        val file = if (data.data is String)
            File(data.data)
        else if (data.data is File)
            data.data
        else
            return false
        if (file.exists() && file.isFile)
            return true
        return false
    }
}