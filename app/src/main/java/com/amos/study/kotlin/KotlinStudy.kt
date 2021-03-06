package com.amos.study.kotlin

import android.util.Log
import kotlinx.coroutines.*
import kotlin.math.sqrt
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




/**
 * @author: amos
 * @date: 2020/12/7 14:50
 * @description:
 */

fun testFunParams(name: String, ){

}


/*fun main() {
    *//*for (i in 1..8 step 2) print(i)
    println()
    for (i in 8 downTo 1 step 2) print(i)


//testString()
    var str1 = "张三"
    var str2 = "张三"
    println(str1 == str2)
    println(str1.equals(str2,true) )

    //var nums = 1..100 //[1,100]
    var nums = 1 until 100 //[1,100)
    var result = 0
    for(num in nums){
        result += num
        print("$num,")
    }
    println()
    println(result)

    runBlocking {

    }
    GlobalScope.launch {  }*//*
    //studyKotlinMethod()
   // calculationSize()

}*/

private val TAG: String = "KotlinStudy"
private var list: ArrayList<Int>? = ArrayList()
private fun studyKotlinMethod() {
    list?.let {
        it.add(1)
        it.add(2)
    }
    println("$list")

    var tempList: ArrayList<Int> = ArrayList()

}

private fun calculationSize() {
    val size = sqrt(750.0 * 750.0 + 1334.0 * 1334.0) / 72.0
    println("size = $size")
}

/**
 * suspend挂起关键字
 */
suspend fun suspendingGetImage(imageId: String) {
    withContext(Dispatchers.IO) {

    }
}

suspend fun delayMethod() {
    delay(1000)
}


fun 获取长方形面积(长: Int, 宽: Int): Int {
    return 长 * 宽
}

fun munToChinese(num: Int): String {
    return when (num) {
        1 -> "一"
        else -> "无法识别"
    }
}

fun testString() {
    var temple = """ 智能诊股说明
1、新增量智能诊股模块
2、展示股票信息；信息包含：股票名称、综合名称
3、点击「股票」跳转至对应的诊股详情页
4、点击「更多」跳转至智能诊股页面
5、诊股的数据源接入实验室的诊断数据，80分以上随机选取3只股票
"""
    println(temple)
}


