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

package com.lt.load_the_image.util

import java.lang.Exception
import java.lang.StringBuilder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object MD5 {
    // 全局数组
    private val strDigits = arrayOf(
        "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
    )

    // 返回形式为数字跟字符串
    private fun byteToArrayString(bByte: Byte): String {
        var iRet = bByte.toInt()
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256
        }
        val iD1 = iRet / 16
        val iD2 = iRet % 16
        return strDigits[iD1] + strDigits[iD2]
    }

    // 返回形式只为数字
    private fun byteToNum(bByte: Byte): String {
        var iRet = bByte.toInt()
        println("iRet1=$iRet")
        if (iRet < 0) {
            iRet += 256
        }
        return iRet.toString()
    }

    // 转换字节数组为16进制字串
    private fun byteToString(bByte: ByteArray): String {
        val sBuffer = StringBuilder()
        for (b in bByte) {
            sBuffer.append(byteToArrayString(b))
        }
        return sBuffer.toString()
    }

    fun GetMD5Code(strObj: String): String? {
        var resultString: String? = null
        try {
            resultString = strObj
            val md = MessageDigest.getInstance("MD5")
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.toByteArray()))
        } catch (ex: NoSuchAlgorithmException) {
            println("MD5.GetMD5Code 54 : $ex")
        }
        return resultString
    }

    fun getMessageDigest(buffer: ByteArray?): String? {
        val hexDigits = charArrayOf(
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            'a',
            'b',
            'c',
            'd',
            'e',
            'f'
        )
        return try {
            val mdTemp = MessageDigest.getInstance("MD5")
            mdTemp.update(buffer)
            val md = mdTemp.digest()
            val j = md.size
            val str = CharArray(j * 2)
            var k = 0
            for (byte0 in md) {
                str[k++] = hexDigits[byte0.toInt() ushr 4 and 0xf]
                str[k++] = hexDigits[byte0.toInt() and 0xf]
            }
            String(str)
        } catch (e: Exception) {
            println("MD5.getMessageDigest 79 : $e")
            null
        }
    }
}