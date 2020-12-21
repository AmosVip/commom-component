package com.amos.study

/**
 * @author: amos
 * @date: 2020/12/7 14:50
 * @description:
 */

fun main(){

    val size = Math.sqrt((1080 * 1080 + 1920 * 1920).toDouble())
    val screenSize = size / 480f
    println("screen size = $screenSize") //5.59
}

class Box<T>(t:T){
    var value = t
}



