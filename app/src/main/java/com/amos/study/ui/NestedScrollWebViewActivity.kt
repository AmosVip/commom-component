package com.amos.study.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amos.study.R
import com.amos.study.utlis.TestInternal
import com.amos.study.utlis.testMethod
import com.amos.study.widget.NestedScrollWebView

/**
 * @author: amos
 * @date: 2020/10/21 15:24
 * @description:
 */
class NestedScrollWebViewActivity : AppCompatActivity() {

    private lateinit var nestedScrollWebView: NestedScrollWebView
    private val url: String =
        "https://www.jianshu.com/p/f09762df81a5"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scroll_webview)
        nestedScrollWebView = findViewById(R.id.nested_scroll_webview)

        onLoadWebView()
         testMethod()
    }

    private fun onLoadWebView() {
        nestedScrollWebView.loadUrl(url)
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, NestedScrollWebViewActivity::class.java))
        }
    }
}