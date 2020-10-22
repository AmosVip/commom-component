package com.amos.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button

/**
 * @author: amos
 * @date: 2020/9/8 18:49
 * @description:
 */
class NestedScrollActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scroll)
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, NestedScrollActivity::class.java))
        }
    }
}