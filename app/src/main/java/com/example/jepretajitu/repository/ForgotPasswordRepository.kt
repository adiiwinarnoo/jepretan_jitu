package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.ForgotPasswordResponse
import com.example.jepretajitu.model.LoginResponse
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordRepository {
    private val apiConfig = ApiConfig()

    fun forgotPassword(email : String, password : String, onResult: (result : ForgotPasswordResponse) -> Unit){
        apiConfig.server.forgotPassword(email,password).enqueue(object : Callback<ForgotPasswordResponse>{
            override fun onResponse(call: Call<ForgotPasswordResponse>, response: Response<ForgotPasswordResponse>) {
                successForgotPassword(response, onResult)
            }

            override fun onFailure(call: Call<ForgotPasswordResponse>, t: Throwable) {
                Log.d("LOGIN-FAILED", "onFailure: ${t.message}")
            }

        })
    }


    fun successForgotPassword(response: Response<ForgotPasswordResponse>, onResult: (result: ForgotPasswordResponse) -> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : ForgotPasswordResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = ForgotPasswordResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : ForgotPasswordResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = ForgotPasswordResponse(message = messageError)
                onResult(default)
            }
        }

    }
}