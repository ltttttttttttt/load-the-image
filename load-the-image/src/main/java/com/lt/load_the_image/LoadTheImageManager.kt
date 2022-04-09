package com.lt.load_the_image

import com.lt.load_the_image.cache.ImageCache
import com.lt.load_the_image.cache.ImageFileCache
import com.lt.load_the_image.cache.ImageLruMemoryCache
import com.lt.load_the_image.loader.DefaultLoadTheImage
import com.lt.load_the_image.loader.HttpLoader
import com.lt.load_the_image.loader.HttpURLConnectionLoader
import com.lt.load_the_image.loader.LoadTheImage
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
     * Load the image
     */
    var loadTheImage: LoadTheImage = DefaultLoadTheImage()
}
