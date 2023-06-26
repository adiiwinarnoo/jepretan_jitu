package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.LoginResponse
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    var apiConfig = ApiConfig()

    fun login(email : String, password : String, onResult : (result : LoginResponse)-> Unit){
        apiConfig.server.login(email,password).enqueue(
            object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    Log.d("LOGIN-FAILED", "onResponse: ${response.code()}")
                    if (response.isSuccessful && response.body() != null){
                        loginSuccess(response,onResult)
                        Response.success(response.body())
                    }else {
                        val messageError = "Login failed"
                        Log.d("LOGIN-FAILED", "onResponse: ${response.code()} and message $messageError")
                        val default = LoginResponse(message = messageError)
                        loginSuccess(response, onResult)
                    }
                }

                override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                    Log.d("LOGIN-FAILED", "onFailure: ${t.message}")
                }
            }
        )
    }



    fun loginSuccess(response: Response<LoginResponse>, onResult: (result: LoginResponse) -> Unit){
        Log.d("LOGIN-FAILED", "onFailure: ${response.code()}")
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                val messageError = Constant.CHECK_CONNECTION
                val default = LoginResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                val messageError = Constant.WRONG_PASSWORD_EMAIL
                val default = LoginResponse(message = messageError)
                onResult(default)
            }
            else -> {
                val messageError = "Unknown error"
                val default = LoginResponse(message = messageError)
                onResult(default)
            }
        }
    }
}