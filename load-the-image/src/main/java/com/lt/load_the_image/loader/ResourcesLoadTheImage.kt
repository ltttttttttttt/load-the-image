package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.ClassLoaderResourceLoader
import androidx.compose.ui.res.painterResource
import com.lt.load_the_image.util.println

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Load the image from resources path, reference [painterResource]
 * warning:
 */
open class ResourcesLoadTheImage : LoadTheImage {
    @Composable
    override fun load(data: DataToBeLoaded): Painter? {
        return painterResource(data.data as? String ?: return null)
    }

    @OptIn(ExperimentalComposeUiApi::class)
    override fun canLoad(data: DataToBeLoaded): Boolean {
        val url = data.data as? String ?: return false
        //Check reference [ClassLoaderResourceLoader]
        val contextClassLoader = Thread.currentThread().contextClassLoader ?: return false
        val resource = try {
            contextClassLoader.getResourceAsStream(url)
                ?: (::ClassLoaderResourceLoader.javaClass).getResourceAsStream(url)
                ?: return false
        } catch (e: Exception) {
            e.println()
            return false
        }
        resource.close()
        return true
    }
}