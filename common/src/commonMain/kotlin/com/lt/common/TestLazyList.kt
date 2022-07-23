package com.lt.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * creator: lt  2022/7/23  lt.dygzs@qq.com
 * effect :
 * warning:
 */
val list = mutableStateListOf(
    "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/e850352ac65c10380e49077eba119313b07e8953.jpg",
    "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/e850352ac65c10380e49077eba119313b07e8953.jpg",
    "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/e850352ac65c10380e49077eba119313b07e8953.jpg",
)

@Composable
fun TestLazyList() {
    Column {
        Button({
            list[0]="C:\\Users\\Administrator\\Pictures\\imageviewer\\1.jpg"
            list.add("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/e850352ac65c10380e49077eba119313b07e8953.jpg")
        }) {
            Text("add")
        }
        LazyColumn {
            items(list) {
                Image(rememberImagePainter(it), "", modifier = Modifier.size(100.dp))
            }
        }
    }
}