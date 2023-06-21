package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.LoginResponse
import com.example.jepretajitu.model.ProfileResponse
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepository {

    var apiConfig = ApiConfig()
    var TAG = "PROFILE-REPO"

    fun getProfile(idUser : Int, onResult: (result : ProfileResponse)-> Unit){
        apiConfig.server.getProfile(idUser).enqueue(object : Callback<ProfileResponse>{
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                getSuccess(response, onResult)
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getSuccess(response : Response<ProfileResponse>, onResult: (result: ProfileResponse) -> Unit){
        when (response.code()) {
            200 -> {
                onResult(response.body()!!)
            }
            400 -> {
                val messageError = Constant.CHECK_CONNECTION
                val default = ProfileResponse(message = messageError)
                onResult(default)
            }
            401 -> {
                val messageError = Constant.WRONG_PASSWORD_EMAIL
                val default = ProfileResponse(message = messageError)
                onResult(default)
            }
            else -> {
                val messageError = "Unknown error"
                val default = ProfileResponse(message = messageError)
                onResult(default)
            }
        }
    }
}