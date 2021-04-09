package com.jds.network

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jds.network.databinding.ActivityTestBinding

/**
 * @author: amos
 * @date: 2021/3/19 14:43
 * @description:
 */
class TestActivity : AppCompatActivity() {
    companion object{
        fun launch(context:Context){
            context.startActivity(Intent(context,TestActivity::class.java))
        }
    }
    private var userLiveData: MutableLiveData<User> = MutableLiveData()
    private lateinit var mBinding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.btnUpdateData.setOnClickListener {
            userLiveData.value = User("Live data")
        }

        userLiveData.observe(this, {
            mBinding.tvName.text = it.name
        })

        //view model
        /*val model : UserViewModel = UserViewModel()
        model.*/

    }
}