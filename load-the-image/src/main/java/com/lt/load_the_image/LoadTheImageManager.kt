/*
 * Copyright lt 2023
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        BitmapLoadTheImage(),
        ImageLoadTheImage(),
        ByteArrayLoadTheImage(),
        InputStreamLoadTheImage(),
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
    fun load(data: DataToBeLoaded): Painter {
        val loader = remember(data.data) {
            loadTheImage.find { it.canLoad(data) }
        }
        if (loader != null)
            return loader.load(data)
                ?: kotlin.run {
                    println("Load the image error: Exception loading URL, data=${data.data}")
                    createErrorPainter(data)
                }

        println("Load the image error: No suitable LoadTheImage found, data=${data.data}")
        return createErrorPainter(data)
    }

    @Composable
    private fun createErrorPainter(data: DataToBeLoaded): Painter {
        val errorImagePath = data.errorImagePath
        if (errorImagePath.isEmpty())
            return remember { EmptyImagePainter() }
        return painterResource(errorImagePath)
    }

    @OptIn(ExperimentalComposeUiApi::class)
    internal fun loadResourceImageBitmap(resourcePath: String) =
        loadImageBitmap(ClassLoaderResourceLoader().load(resourcePath))
}
