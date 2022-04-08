package com.lt.load_the_image.loader

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Load network resource to byteArray
 * warning:
 */
fun interface HttpLoader {

    /**
     * Load network resource to byteArray
     * [url]Network url
     */
    fun load(url: String): ByteArray?
}