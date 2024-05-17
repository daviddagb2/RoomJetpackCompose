package com.gonzalez.blanchard.testroom.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gonzalez.blanchard.testroom.database.AppDatabase
import com.gonzalez.blanchard.testroom.database.User
import com.gonzalez.blanchard.testroom.database.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao: UserDao
    private val db = AppDatabase.getInstance(application)

    init {
        userDao = db.userDao()
    }

    // LiveData to observe users
    val users: LiveData<List<User>> = userDao.getAllUsers().asLiveData()

    fun insertUser(name: String, age: Int){
        viewModelScope.launch {
            val quoteDao = db.userDao()
            val userEntity = User(
                name = name,
                age = age
            )
            quoteDao.insertUser(userEntity)
        }
    }

}