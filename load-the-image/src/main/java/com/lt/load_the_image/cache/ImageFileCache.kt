package com.lt.load_the_image.cache

import java.io.File
import java.net.URLEncoder

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Native file cache configuration of network image
 * warning: [cacheDir] Native file cache dir
 */
open class ImageFileCache(
    private val cacheDir: File = createCacheDir()
) : ImageCache<ByteArray> {

    init {
        if (!cacheDir.exists() || !cacheDir.isDirectory)
            cacheDir.mkdirs()
    }

    override fun saveCache(url: String, t: ByteArray) {
        try {
            val file = File(cacheDir, urlToFileName(url))
            if (file.exists())
                file.delete()
            file.createNewFile()
            file.writeBytes(t)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getCache(url: String): ByteArray? {
        try {
            val file = File(cacheDir, urlToFileName(url))
            if (file.exists())
                return file.readBytes()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    //Convert url to file name
    private fun urlToFileName(url: String): String {
        return URLEncoder.encode(url, "UTF-8")
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