package com.lt.load_the_image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.ClassLoaderResourceLoader
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import com.lt.load_the_image.cache.ImageCache
import com.lt.load_the_image.cache.ImageFileCache
import com.lt.load_the_image.cache.ImageLruMemoryCache
import com.lt.load_the_image.loader.*
import com.lt.load_the_image.painter.DefaultPainterCreator
import com.lt.load_the_image.painter.HttpImagePainter
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
     * The path of the picture displayed when an exception occurred while loading the picture
     */
    var defaultErrorImagePath = ""

    /**
     * Load the image
     */
    @Composable
    fun load(url: String): Painter {
        if (url.isEmpty()) {
            println("Load the image error: Url is Empty")
            return createErrorPainter()
        }
        val loader = remember {
            loadTheImage.find { it.canLoad(url) }
        }
        if (loader != null)
            return loader.load(url)
                ?: kotlin.run {
                    println("Load the image error: Exception loading URL")
                    createErrorPainter()
                }

        println("Load the image error: No suitable LoadTheImage found")
        return createErrorPainter()
    }

    @Composable
    private fun createErrorPainter(): Painter {
        if (defaultErrorImagePath.isEmpty())
            return HttpImagePainter()
        return painterResource(defaultErrorImagePath)
    }

    @OptIn(ExperimentalComposeUiApi::class)
    internal fun loadResourceImageBitmap(resourcePath: String) =
        loadImageBitmap(ClassLoaderResourceLoader().load(resourcePath))
}
