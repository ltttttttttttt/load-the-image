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

import com.lt.load_the_image.util.println
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
            e.println()
            null
        }
    }
}