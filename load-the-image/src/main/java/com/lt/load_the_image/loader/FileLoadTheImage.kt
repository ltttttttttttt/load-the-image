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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import com.lt.load_the_image.LoadTheImageManager
import com.lt.load_the_image.painter.AsyncImagePainter
import com.lt.load_the_image.util.println
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        val painter = remember(file.absolutePath) { AsyncImagePainter() }
        LaunchedEffect(file.absolutePath) {
            withContext(Dispatchers.IO) {
                val byteArray = LoadTheImageManager.memoryCache.getCache(file.absolutePath) ?: try {
                    file.readBytes()
                } catch (e: Exception) {
                    e.println()
                    painter.imageBitmap.value =
                        LoadTheImageManager.loadResourceImageBitmap(data.errorImagePath)
                    return@withContext
                }
                LoadTheImageManager.memoryCache.saveCache(file.absolutePath, byteArray)
                LoadTheImageManager.painterCreator.create(byteArray)
                painter.imageBitmap.value =
                    LoadTheImageManager.painterCreator.createImageBitmap(byteArray)
            }
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