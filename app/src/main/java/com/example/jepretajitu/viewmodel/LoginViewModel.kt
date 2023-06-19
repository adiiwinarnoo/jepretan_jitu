package com.example.jepretajitu.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.LoginResponse
import com.example.jepretajitu.repository.LoginRepository

class LoginViewModel : ViewModel() {

    var loginRepository = LoginRepository()
    var loginData = MutableLiveData<LoginResponse>()

    fun login(email : String, password : String) : MutableLiveData<LoginResponse>{
        loginRepository.login(email, password){
            loginData.postValue(it)
        }

        return loginData
    }

}