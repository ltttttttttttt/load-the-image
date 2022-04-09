package com.lt.load_the_image.cache

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Memory cache configuration of network image, cache use LRU
 * warning: [maxMemorySize] Byte maximum memory usage
 */
open class ImageLruMemoryCache(
    private val maxMemorySize: Long = getMemoryWithOnePercent()
) : ImageCache {
    //image lru cache
    private val cacheMap = LinkedHashMap<String, ByteArray>(35, 1f, true)

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