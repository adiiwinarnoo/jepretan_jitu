package com.example.jepretajitu.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.RegisterResponse
import com.example.jepretajitu.model.UbahProfileResponse
import com.example.jepretajitu.repository.RegisterRepository

class RegisterViewModel : ViewModel() {

    private val registerRepository = RegisterRepository()
    var registerData = MutableLiveData<RegisterResponse>()
    var dataRegister : LiveData<RegisterResponse> = registerData
    var ubahDataProfile = MutableLiveData<UbahProfileResponse>()

    fun sendRegister(name : String, email : String,nomorHp : String, password : String,levelId : Int, imageFoto : String? = null,alamat : String) : MutableLiveData<RegisterResponse>{
        registerRepository.register(name, email,nomorHp, password,levelId,imageFoto,alamat){
            registerData.postValue(it)
        }

        return registerData
    }

    fun sendUpdateProfile(idUser : Int, name : String, email : String,nomorHp : String,alamat : String,imageFoto : String?) : MutableLiveData<UbahProfileResponse>{
        registerRepository.updateProfile(idUser,name,email,nomorHp,alamat,imageFoto){
            ubahDataProfile.postValue(it)
        }
        return ubahDataProfile
    }

}