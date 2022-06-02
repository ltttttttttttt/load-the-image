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
import com.lt.load_the_image.painter.EmptyImagePainter
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
        BitmapLoadTheImage(),
        ImageLoadTheImage(),
        ByteArrayLoadTheImage(),
        InputStreamLoadTheImage(),
    )

    /**
     * The path of the picture displayed when an exception occurred while loading the picture
     */
    var defaultErrorImagePath = ""

    /**
     * Load the image
     */
    @Composable
    fun load(data: DataToBeLoaded): Painter {
        val loader = remember {
            loadTheImage.find { it.canLoad(data) }
        }
        if (loader != null)
            return loader.load(data)
                ?: kotlin.run {
                    println("Load the image error: Exception loading URL")
                    createErrorPainter(data)
                }

        println("Load the image error: No suitable LoadTheImage found")
        return createErrorPainter(data)
    }

    @Composable
    private fun createErrorPainter(data: DataToBeLoaded): Painter {
        val errorImagePath = data.errorImagePath
        if (errorImagePath.isEmpty())
            return EmptyImagePainter()
        return painterResource(errorImagePath)
    }

    @OptIn(ExperimentalComposeUiApi::class)
    internal fun loadResourceImageBitmap(resourcePath: String) =
        loadImageBitmap(ClassLoaderResourceLoader().load(resourcePath))
}
