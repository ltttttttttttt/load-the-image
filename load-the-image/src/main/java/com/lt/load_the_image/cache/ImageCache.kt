package com.lt.load_the_image.cache

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Cache configuration of network image
 * warning:
 */
interface ImageCache {

    /**
     * Save image Cache
     * [url]Unique key
     * [t]The cache
     */
    fun saveCache(url: String, t: ByteArray)

    /**
     * Get image Cache
     * [url]Unique key
     */
    fun getCache(url: String): ByteArray?
}