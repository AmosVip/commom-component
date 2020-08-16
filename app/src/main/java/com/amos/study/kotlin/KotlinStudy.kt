package com.amos.study.kotlin

/**
 * @author: amos
 * @date: 2020/8/12 13:40
 * @description:
 */

fun main() {
    studySequence()
}


/**
 * 序列
 */
private fun studySequence() {
    val sequence = sequenceOf("Android", "iOS")

    println("sequence = $sequence")
    val iterator = sequence.iterator();
    while (iterator.hasNext()) {
        val value = iterator.next();
        println("sequence iterator = $value")
    }

    val stringList = listOf("Java","Kotlin","OC")
    val stringSequence = stringList.asSequence();
    println("stringSequence = $stringSequence")
    //创建序列的另一种方法是通过使用计算其元素的函数来构建序列。
    // 要基于函数构建序列，请以该函数作为参数调用 generateSequence()。
    // （可选）可以将第一个元素指定为显式值或函数调用的结果。 当提供的函数返回 null 时，序列生成停止
    val addNumbers = generateSequence(1) {it + 2  }
    println(addNumbers.take(4).toList())

}