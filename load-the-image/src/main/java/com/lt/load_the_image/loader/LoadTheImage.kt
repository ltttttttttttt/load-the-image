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

package com.lt.load_the_image.loader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import com.lt.load_the_image.LoadTheImageManager

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Load the image: url to Painter
 * warning:
 */
interface LoadTheImage {

    /**
     * Complete process with load image
     */
    @Composable
    fun load(data: DataToBeLoaded): Painter?

    /**
     * Return false means it cannot be processed
     */
    fun canLoad(data: DataToBeLoaded): Boolean
}

/**
 * Data loaded in [LoadTheImage]
 * [data]The data
 */
@Stable
open class DataToBeLoaded(val data: Any) {
    /**
     * Bitmap when loading network pictures (resource)
     */
    var placeholderResource: String = ""

    /**
     * Whether to close the stream automatically when [data] is a stream
     */
    var isAutoCloseStream: Boolean = true

    /**
     * The path of the picture displayed when an exception occurred while loading the picture
     */
    var errorImagePath = LoadTheImageManager.defaultErrorImagePath
}

/**
 * Remember the DataToBeLoaded
 */
@Composable
fun rememberDataToBeLoaded(data: Any) = remember(data) { DataToBeLoaded(data) }