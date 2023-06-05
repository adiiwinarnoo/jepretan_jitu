package com.example.jepretajitu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.ForgotPasswordResponse
import com.example.jepretajitu.repository.ForgotPasswordRepository

class ForgotPasswordViewModel : ViewModel() {

    private var forgotPasswordRepository = ForgotPasswordRepository()
    var forgotPasswordData =  MutableLiveData<ForgotPasswordResponse>()

    fun sendForgotPassword(email : String, password : String) : MutableLiveData<ForgotPasswordResponse>{
        forgotPasswordRepository.forgotPassword(email,password){
            forgotPasswordData.postValue(it)
        }
        return forgotPasswordData
    }

}