package com.amos.study

import androidx.viewpager.widget.ViewPager

/**
 * @author: amos
 * @date: 2020/8/20 13:23
 * @description:
 */

fun main() {
    testClass()
    testObject()
    testExtends()
    testObjectParent()
    RawStringClass().rawStringMethod()
    CollectionClass().filterMethod()
    CollectionClass().mapMethod()
}

private fun testClass() {
    val user = User(1, "Kotlin")
}

private fun testObject() {
    println("${Sample.name}")
    Sample.method()
}

private fun testExtends() {
    Sample.aMethod()
    Sample.bMethod()
    Sample.interfaceMethod()
}

private fun testObjectParent() {
    ObjectParent.Sample.printlnMethod()
    ObjectParent.companionMethod()
}

class User(id: Int) {
    private var id: Int = 0
    private var name: String = ""

    val size: Int
        get() {
            return 3
        }


    init {
        // 初始化代码块，先于下面的构造器执行
        println("init init ")
    }

    constructor(id: Int, name: String) : this(id) {
        this.id = id
        this.name = name
        println("constructor  constructor ")
    }
}

/**
 * 这种通过 object 实现的单例是一个饿汉式的单例，并且实现了线程安全。
 * Kotlin 中不仅类可以继承别的类，可以实现接口，object 也可以
 */
object Sample : ParentA(), InterfaceParent {
    val name = "Sample name"

    fun method() {
        println("Sample Method")
    }

    override fun bMethod() {
        super.bMethod()
    }

    override fun interfaceMethod() {
        super.interfaceMethod()
    }
}

open class ParentA {
    fun aMethod() {
        println("ParentA  aMethod")
    }

    open fun bMethod() {
        println("ParentA  bMethod")
    }
}

interface InterfaceParent {
    fun interfaceMethod() {

    }
}

class ObjectParent {

    object Sample {
        //Kotlin 的常量必须声明在对象（包括伴生对象）或者「top-level 顶层」中，因为常量是静态的。
        //Kotlin 新增了修饰常量的 const 关键字。
        const val c: Int = 2
        fun printlnMethod() {
            println("ObjectParent Sample c = $c")
        }
    }

    //一个类中最多只可以有一个伴生对象，但可以有多个嵌套对象
    //当有 companion 修饰时，对象的名字也可以省略掉
    companion object /*A*/ {
        fun companionMethod() {
            println("ObjectParent A companionMethod ")
        }
    }


}

// kotlin 匿名类
val listener = object : ViewPager.SimpleOnPageChangeListener() {
    override fun onPageSelected(position: Int) {
        // override
    }
}

//顶层声明
fun topLevelFunction() {

}

//数组
class ArrayClass {
    val strAry: Array<String> = arrayOf("a")
    val intAry: IntArray = intArrayOf(1)

    fun aryMethod() {

    }
}

//集合
class CollectionClass {

    val strList = listOf("")
    val strAryList = arrayListOf("")
    val strSet = setOf("")
    val strMap = mapOf("a" to 1)
    val strLinkMap = linkedMapOf("a" to 1)
    val strHashMap = hashMapOf("a" to 1)
    val mutableMap = mutableMapOf("a" to 1)

    fun collectionMethod() {

    }

    //list filter
    fun filterMethod() {
        val intArray: IntArray = intArrayOf(1, 3, 5, 6, 8)
        val newList: List<Int> = intArray.filter { i ->
            i != 1
        }
        println("new list = ${newList.toString()}")
    }

    //list map
    fun mapMethod() {
        val intArray: IntArray = intArrayOf(1, 3, 5, 6, 8)
        val newList: List<Int> = intArray.map { i ->
            i + 1
        }
        println("new list = ${newList.toString()}")
    }

    //list flat map
    fun flatMapMethod() {
        val intArray: IntArray = intArrayOf(1, 3, 5, 6, 8)
        //intArray.flatMap {  }
    }

}

//嵌套函数
class NestedFunctionClass {

    fun login(userName: String, password: String) {


        fun validate(value: String) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("参数异常")
            }
        }

        validate(userName)
        validate(password)
    }
}

//原生字符串
//有时候我们不希望写过多的转义字符，这种情况 Kotlin 通过「原生字符串」来实现。
//用法就是使用一对 """ 将字符串括起来：
//\n 并不会被转义
//最后输出的内容与写的内容完全一致，包括实际的换行
//$ 符号引用变量仍然生效
class RawStringClass {

    //但对齐方式看起来不太优雅，原生字符串还可以通过 trimMargin() 函数去除每行前面的空格：
    //| 符号为默认的边界前缀，前面只能有空格，否则不会生效
    //输出时 | 符号以及它前面的空格都会被删除
    //边界前缀还可以使用其他字符，比如 trimMargin("/")，只不过上方的代码使用的是参数默认值的调用方式
    fun rawStringMethod() {
        val name = "world"
        val myName = "kotlin"
        val text = """
            |Hi $name!
            |My name is $myName.\n
        """.trimMargin()
        println(text)
    }
}

class RangeClass {

}

//高阶函数
class FunParamsClass {
    //函数类型的参数
    fun funParamsMethod(funParams: (Int) -> String): String {
        return funParams(1)
    }

    //同样的，函数类型不只可以作为函数的参数类型，还可以作为函数的返回值类型：
    /*fun funReturnValue(params: Int): (Int) -> Unit {

    }*/
}