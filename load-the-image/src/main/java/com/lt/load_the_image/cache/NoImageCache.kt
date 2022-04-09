package com.lt.load_the_image.cache

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Do not use cache
 * warning:
 */
class NoImageCache : ImageCache {
    override fun saveCache(url: String, t: ByteArray) {
    }

    override fun getCache(url: String): ByteArray? = null
}