package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.RegisterResponse
import com.example.jepretajitu.network.ApiConfig
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterRepository {

    val apiConfig = ApiConfig()

    fun register(name : String, email : String,nomorHp : String, password : String,levelId : Int,
                 imageFoto : String? = null,alamat : String, onResult : (result : RegisterResponse)-> Unit){
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

}