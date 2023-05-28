package com.example.jepretajitu.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.RegisterResponse
import com.example.jepretajitu.repository.RegisterRepository

class RegisterViewModel : ViewModel() {

    private val registerRepository = RegisterRepository()
    var registerData = MutableLiveData<RegisterResponse>()
    var dataRegister : LiveData<RegisterResponse> = registerData

    fun sendRegister(name : String, email : String,nomorHp : String, password : String,levelId : Int, imageFoto : String? = null,alamat : String) : MutableLiveData<RegisterResponse>{
        registerRepository.register(name, email,nomorHp, password,levelId,null,alamat){
            Log.d("data-register", "sendRegister: $it")
            registerData.postValue(it)
        }

        return registerData
    }

}