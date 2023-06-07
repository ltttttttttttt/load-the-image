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

package com.lt.load_the_image.painter

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter

/**
 * creator: lt  2022/4/8  lt.dygzs@qq.com
 * effect : Create [Painter] from ByteArray
 * warning:
 */
interface PainterCreator {

    /**
     * Create [Painter] from [byteArray]
     */
    fun create(byteArray: ByteArray): Painter

    /**
     * Create [ImageBitmap] from [byteArray]
     */
    fun createImageBitmap(byteArray: ByteArray): ImageBitmap
}