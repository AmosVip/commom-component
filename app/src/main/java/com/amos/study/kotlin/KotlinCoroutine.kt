package com.amos.study.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author: amos
 * @date: 2020/8/12 18:36
 * @description:  kotlin 协程
 *
 */

fun main() {
    studyCoroutine()
}

private fun studyCoroutine(){
    GlobalScope.launch {
        delay(1000L)
        println("hello world")
    }
    println("HELLO WORLD")
    //Thread.sleep(2000L)
}