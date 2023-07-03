package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.GetProductResponse
import com.example.jepretajitu.model.ReviewResponse
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetProductRepository {

    var apiConfig = ApiConfig()

    fun getProductById(idProduct : Int, result : (result : GetProductResponse)-> Unit){
        apiConfig.server.getProduct(idProduct).enqueue(object : Callback<GetProductResponse>{
            override fun onResponse(call: Call<GetProductResponse>, response: Response<GetProductResponse>) {
                getProductSuccess(response,result)
            }

            override fun onFailure(call: Call<GetProductResponse>, t: Throwable) {
                Log.d("GET-PRODUCT", "onFailure: ${t.message}")
            }

        })
    }

    fun getProductSuccess(response: Response<GetProductResponse>, onResult: (result: GetProductResponse) -> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : GetProductResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = GetProductResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : GetProductResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = GetProductResponse(message = messageError)
                onResult(default)
            }
        }
    }
}