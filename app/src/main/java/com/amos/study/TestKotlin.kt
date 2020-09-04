package com.amos.study

/**
 * @author: amos
 * @date: 2020/8/20 13:23
 * @description:
 */

fun main() {
    val sourceList = mutableListOf<A>(A())
    println("sourceList = ${sourceList[0].name} +${sourceList[0].type[0].type}")
    val copyList = ArrayList<A>()
    copyList.add(A(sourceList[0].name, sourceList[0].age))
    copyList[0].name = "iOS"
    copyList[0].age = 200
    copyList[0].type = sourceList[0].type
    copyList[0].type[0].type = "Java"
    println("copyList = ${copyList[0].name} +${copyList[0].type[0].type}")
    println("sourceList = ${sourceList[0].name}+${sourceList[0].type[0].type}")

}

class A() {
    var name: String = "Android"
    var age: Int = 18
    var type: ArrayList<B> = arrayListOf(B())

    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }
}

class B() {
    var type: String = "Kotlin"
}
