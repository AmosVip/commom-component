package com.amos.study.kotlin

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * <pre>
 *     author : amos
 *     time   : 2020/08/10 19:36
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class KotlinMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        listOf("Android","iOS")
        mutableListOf("Kotlin","Java")
        //Map 也有这样的函数 mapOf() 与 mutableMapOf()。映射的键和值作为 Pair 对象传递（通常使用中缀函数 to 创建）。
        //val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
        //注意，to 符号创建了一个短时存活的 Pair 对象，因此建议仅在性能不重要时才使用它。 为避免过多的内存使用，
        // 请使用其他方法。例如，可以创建可写 Map 并使用写入操作填充它。 apply() 函数可以帮助保持初始化流畅。
        val numbersMap = mutableMapOf<String, String>().apply { this["one"] = "1"; this["two"] = "2" }

        var empty = emptyList<String>()
        var list = List(3) { it*2}
        //var aryList = LinkedList()


    }
}