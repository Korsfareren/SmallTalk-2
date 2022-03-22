package com.example.smalltalk.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.smalltalk.database.AppDatabase
import com.example.smalltalk.database.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val database = AppDatabase.getInstance(getApplication())
    val userDao = database.userDao()

    fun checkLogin(loginUsername: String, loginPassword: String): Boolean {
        return loginUsername == "" && loginPassword == ""
    }

    fun saveUserToDatabase(user: User, callback: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.removeAllUsers()
            userDao.saveUser(user)
            callback()
        }.start()
    }
}