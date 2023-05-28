package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.LoginResponse
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.utils.Constant
import retrofit2.Response

class LoginRepository {

    private val apiConfig = ApiConfig()

    fun login(email : String, password : String, onResult : (result : LoginResponse)-> Unit){
        apiConfig.server.login(email,password).enqueue(
            object : retrofit2.Callback<LoginResponse>{
                override fun onResponse(call: retrofit2.Call<LoginResponse>, response: retrofit2.Response<LoginResponse>) {
                    loginSuccess(response,onResult)
                }

                override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                    Log.d("LOGIN-FAILED", "onFailure: ${t.message}")
                }
            }
        )
    }



    fun loginSuccess(response: Response<LoginResponse>, onResult: (result: LoginResponse) -> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : LoginResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = LoginResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : LoginResponse? = null
                val messageError = Constant.WRONG_PASSWORD_EMAIL
                default = LoginResponse(message = messageError)
                onResult(default)
            }
        }
    }
}