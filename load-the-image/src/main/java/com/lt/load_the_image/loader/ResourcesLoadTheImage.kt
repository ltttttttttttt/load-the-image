package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.ClassLoaderResourceLoader
import androidx.compose.ui.res.painterResource

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Load the image from resources path, reference [painterResource]
 * warning:
 */
open class ResourcesLoadTheImage : LoadTheImage {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun load(url: String): Painter? {
        //Check reference [ClassLoaderResourceLoader]
        remember {
            val contextClassLoader = Thread.currentThread().contextClassLoader!!
            val resource =try{ contextClassLoader.getResourceAsStream(url)
                ?: (::ClassLoaderResourceLoader.javaClass).getResourceAsStream(url)
                ?: return null}
            catch (e:Exception){
                println("lllttt")
                e.printStackTrace()
                null
            }
            resource?.close()
        }
        return painterResource(url)
    }
}