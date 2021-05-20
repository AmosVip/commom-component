package com.amos.study.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.amos.study.R
import com.amos.study.base.BaseActivity

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