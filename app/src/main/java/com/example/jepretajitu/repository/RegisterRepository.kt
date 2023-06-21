package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.LoginResponse
import com.example.jepretajitu.model.RegisterResponse
import com.example.jepretajitu.model.UbahProfileResponse
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.utils.Constant
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterRepository {

    private val apiConfig = ApiConfig()

    fun register(name : String, email : String,nomorHp : String, password : String,levelId : Int,
                 imageFoto : String?,alamat : String, onResult : (result : RegisterResponse)-> Unit){
        apiConfig.server.register(name,email,nomorHp,password,levelId,imageFoto,alamat).enqueue(
            object : Callback<RegisterResponse>{
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    registerSuccess(response,onResult)

                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.d("FAILED-REGISTER", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun updateProfile(idUser : Int, name : String, email : String,nomorHp : String,alamat : String,imageFoto : String?,
                      onResult : (result : UbahProfileResponse)-> Unit){
        apiConfig.server.ubahProfile(idUser,name,email,nomorHp,alamat,imageFoto).enqueue(object : Callback<UbahProfileResponse>{
            override fun onResponse(call: Call<UbahProfileResponse>, response: Response<UbahProfileResponse>) {
                ubahSuccess(response,onResult)
            }

            override fun onFailure(call: Call<UbahProfileResponse>, t: Throwable) {
                Log.d("REGISTER-FAILED", "onFailure: ${t.message}")
            }

        })
    }



    fun registerSuccess(response: Response<RegisterResponse>, onResult: (result: RegisterResponse) -> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : RegisterResponse? = null
                val messageError = "Your email is registered!"
                default = RegisterResponse(message = messageError)
                onResult(default)
            }
        }
    }

    fun ubahSuccess(response: Response<UbahProfileResponse>, onResult: (result: UbahProfileResponse) -> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                val messageError = Constant.CHECK_CONNECTION
                val default = UbahProfileResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                val messageError = Constant.WRONG_PASSWORD_EMAIL
                val default = UbahProfileResponse(message = messageError)
                onResult(default)
            }
            else -> {
                val messageError = "Unknown error"
                val default = UbahProfileResponse(message = messageError)
                onResult(default)
            }
        }
    }

}