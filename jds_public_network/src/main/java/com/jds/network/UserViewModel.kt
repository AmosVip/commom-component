package com.jds.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author: amos
 * @date: 2021/3/19 17:20
 * @description:
 */
class UserViewModel : ViewModel() {
    private val users : MutableLiveData<User> by lazy {
        MutableLiveData<User>().also {
            loadUsers()
        }
    }

    fun getUsersMethod(): LiveData<User>{
        return users
    }

    private fun loadUsers(){

    }
}