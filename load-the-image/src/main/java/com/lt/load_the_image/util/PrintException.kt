package com.lt.load_the_image.util

/**
 * Print [Exception]
 */
fun Exception.println() {
    RuntimeException("Load-The-Image inner exception", this).printStackTrace()
}
