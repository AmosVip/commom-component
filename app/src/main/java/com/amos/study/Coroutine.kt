package com.amos.study

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 * @author: amos
 * @date: 2020/11/9 14:04
 * @description:  kotlin协程
 */

fun main() {
    coroutine()
}


fun coroutine() {

    /*GlobalScope.launch {
        delay(1000L)
        println("World")
    }
    println("Hello")
    Thread.sleep(2000L)*/

    thread {
        Thread.sleep(1000L)
        println("World")
    }
    println("Hello")
    Thread.sleep(2000L)
}