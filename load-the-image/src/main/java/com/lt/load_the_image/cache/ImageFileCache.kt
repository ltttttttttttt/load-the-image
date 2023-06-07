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

package com.lt.load_the_image.cache

import com.lt.load_the_image.util.MD5
import com.lt.load_the_image.util.println
import java.io.File

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Native file cache configuration of network image
 * warning: [cacheDir] Native file cache dir
 */
open class ImageFileCache(
    private val cacheDir: File = createCacheDir()
) : ImageCache {

    init {
        if (!cacheDir.exists() || !cacheDir.isDirectory)
            cacheDir.mkdirs()
    }

    override fun saveCache(url: String, t: ByteArray) {
        try {
            val file = File(cacheDir, urlToFileName(url))
            if (file.exists()) {
                if (file.length() == t.size.toLong())
                    return
                file.delete()
            }
            file.createNewFile()
            file.writeBytes(t)
        } catch (e: Exception) {
            e.println()
        }
    }

    override fun getCache(url: String): ByteArray? {
        try {
            val file = File(cacheDir, urlToFileName(url))
            if (file.exists())
                return file.readBytes()
        } catch (e: Exception) {
            e.println()
        }
        return null
    }

    //Convert url to file name
    private fun urlToFileName(url: String): String {
        return MD5.GetMD5Code(url) + url.length + url.hashCode() + ".jpg"
    }
}

//Create cache dir
private fun createCacheDir() = File(
    System.getProperty("user.home")
            + File.separator
            + "Pictures"
            + File.separator
            + "LoadTheImageCache"
            + File.separator
)