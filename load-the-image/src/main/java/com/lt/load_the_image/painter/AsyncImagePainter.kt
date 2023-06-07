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

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.IntSize
import kotlin.math.roundToInt

/**
 * creator: lt  2022/5/31  lt.dygzs@qq.com
 * effect : Image painter with async.
 * warning:
 */
open class AsyncImagePainter(
    imageBitmap: ImageBitmap? = null
) : Painter() {
    val imageBitmap: MutableState<ImageBitmap?> = mutableStateOf(imageBitmap)

    override val intrinsicSize: Size
        get() {
            val bitmap = imageBitmap.value ?: return Size(1f, 1f)
            return Size(bitmap.width.toFloat(), bitmap.height.toFloat())
        }

    override fun DrawScope.onDraw() {
        val bitmap = imageBitmap.value ?: return
        drawImage(
            bitmap,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )
    }
}