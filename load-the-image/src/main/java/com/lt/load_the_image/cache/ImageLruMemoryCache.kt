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

import java.util.Collections

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Memory cache configuration of network image, cache use LRU
 * warning: [maxMemorySize] Byte maximum memory usage
 */
open class ImageLruMemoryCache(
    private val maxMemorySize: Long = getMemoryWithOnePercent()
) : ImageCache {
    //image lru cache
    private val cacheMap = Collections.synchronizedMap(
        LinkedHashMap<String, ByteArray>(35, 1f, true)
    )

    //image cache byte size sum
    private var cacheSize: Long = 0

    override fun saveCache(url: String, t: ByteArray) {
        if (t.size > maxMemorySize)
            return
        cacheMap[url] = t
        cacheSize += t.size
        while (cacheSize > maxMemorySize && cacheMap.isNotEmpty()) {
            val byteArray = cacheMap.remove(cacheMap.keys.first())
            cacheSize -= byteArray?.size ?: 0
        }
    }

    override fun getCache(url: String): ByteArray? {
        return cacheMap[url]
    }
}

//Gets one percent of the total memory
private fun getMemoryWithOnePercent(): Long {
    return maxOf(50L * 1024 * 1024, Runtime.getRuntime().maxMemory() / 100)
}