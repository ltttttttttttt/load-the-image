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
        val url = data.data as? String
        if (url.isNullOrEmpty()) return false
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