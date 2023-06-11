package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.DataKatalogResponse
import com.example.jepretajitu.model.ForgotPasswordResponse
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LihatKatalogRepository {

    var TAG = "KATALOG-"
    private val apiConfig = ApiConfig()

    fun getKatalogAll(onResult : (result : DataKatalogResponse) -> Unit){
        apiConfig.server.getKatalogAll().enqueue(object : Callback<DataKatalogResponse>{
            override fun onResponse(call: Call<DataKatalogResponse>, response: Response<DataKatalogResponse>) {
                getKatalogSuccess(response,onResult)
            }

            override fun onFailure(call: Call<DataKatalogResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getKatalogSuccess(response: Response<DataKatalogResponse>, onResult: (result: DataKatalogResponse) -> Unit){
        Log.d(TAG, "getKatalogSuccess-code: ${response.code()}")
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : DataKatalogResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = DataKatalogResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : DataKatalogResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = DataKatalogResponse(message = messageError)
                onResult(default)
            }
        }
    }

}