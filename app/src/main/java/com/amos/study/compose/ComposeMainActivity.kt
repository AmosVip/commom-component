package com.amos.study.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.platform.setContent
import com.amos.study.base.BaseActivity

/**
 * @author: amos
 * @date: 2021/3/9 15:02
 * @description:
 */
class ComposeMainActivity : BaseActivity() {
    companion object{
        fun launch(context: Context){
            context.startActivity(Intent(context,ComposeMainActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* setContent {
            Text("Compose Study")
        }*/
    }


}