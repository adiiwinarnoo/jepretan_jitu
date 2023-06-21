package com.example.jepretajitu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.ProfileResponse
import com.example.jepretajitu.repository.ProfileRepository

class ProfileViewModel : ViewModel() {

    var profileRepository = ProfileRepository()
    var dataProfile = MutableLiveData<ProfileResponse>()

    fun getProfile(idUser : Int) : MutableLiveData<ProfileResponse>{
        profileRepository.getProfile(idUser){
            dataProfile.postValue(it)
        }
        return dataProfile
    }

}