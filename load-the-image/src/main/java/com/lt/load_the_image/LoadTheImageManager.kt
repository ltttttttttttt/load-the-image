package com.lt.load_the_image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.lt.load_the_image.cache.ImageCache
import com.lt.load_the_image.cache.ImageFileCache
import com.lt.load_the_image.cache.ImageLruMemoryCache
import com.lt.load_the_image.loader.*
import com.lt.load_the_image.painter.DefaultPainterCreator
import com.lt.load_the_image.painter.PainterCreator

/**
 * Set LoadTheImage settings
 */
object LoadTheImageManager {
    /**
     * Set is use the memory cache
     */
    var memoryCache: ImageCache = ImageLruMemoryCache()

    /**
     * Set is use the file cache
     */
    var fileCache: ImageCache = ImageFileCache()

    /**
     * Load network resource to byteArray
     */
    var httpLoader: HttpLoader = HttpURLConnectionLoader()

    /**
     * Create Painter from ByteArray
     */
    var painterCreator: PainterCreator = DefaultPainterCreator()

    /**
     * Handler load image
     */
    var loadTheImage: MutableList<LoadTheImage> = mutableListOf(
        HttpLoadTheImage(),
        FileLoadTheImage(),
        ResourcesLoadTheImage(),
    )

    /**
     * Load the image
     */
    @Composable
    fun load(url: String): Painter {
        // TODO by lt 2022/4/8 18:23 需要处理线程切换和默认占位对象和占位图等的处理,异常和获取不到图片等情况的处理,加载本地图片和资源,空的处理
        if (url.isEmpty())
            return painterResource("drawable-xxhdpi/load_error.jpeg")// TODO by lt 2022/4/10 11:11  处理一下
        val loader = remember {
            loadTheImage.find { it.canLoad(url) }
        }
        if (loader != null)
            return loader.load(url)
                ?: painterResource("drawable-xxhdpi/load_error.jpeg")// TODO by lt 2022/4/10 11:11  处理一下
        return painterResource("drawable-xxhdpi/load_error.jpeg")// TODO by lt 2022/4/10 11:11  处理一下
    }
}
