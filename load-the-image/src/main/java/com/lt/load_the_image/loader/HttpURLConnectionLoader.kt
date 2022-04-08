package com.lt.load_the_image.loader

import java.net.URL

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Use HttpURLConnection load url
 * warning:
 */
open class HttpURLConnectionLoader : HttpLoader {
    override fun load(url: String): ByteArray? {
        return try {
            URL(url).readBytes()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}