package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.*
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

    fun getKatalogById(idUser : Int, onResult: (result: KatalogByIdResponse) -> Unit){
        apiConfig.server.getKatalogById(idUser).enqueue(object : Callback<KatalogByIdResponse>{
            override fun onResponse(call: Call<KatalogByIdResponse>, response: Response<KatalogByIdResponse>) {
                getKatalogByIdSuccess(response,onResult)
            }

            override fun onFailure(call: Call<KatalogByIdResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getReview(idProduct : Int, onResult: (result: ReviewResponse) -> Unit){
        apiConfig.server.getReview(idProduct).enqueue(object : Callback<ReviewResponse>{
            override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                getReviewSuccess(response,onResult)
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun uploadReview(idProduct : Int,idUser: Int,review : String,rating : Int, onResult: (result: UploadReviewResponse) -> Unit){
        apiConfig.server.uploadReview(idProduct,idUser,review,rating).enqueue(object : Callback<UploadReviewResponse>{
            override fun onResponse(call: Call<UploadReviewResponse>, response: Response<UploadReviewResponse>) {
                Log.d("UPLOAD-REVIEWW", "onResponse: ${response.body()}")
                uploadReviewSuccess(response,onResult)
            }

            override fun onFailure(call: Call<UploadReviewResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getKatalogSuccess(response: Response<DataKatalogResponse>, onResult: (result: DataKatalogResponse) -> Unit){
        Log.d(TAG, "getKatalogSuccess-code: ${response.code()}")
        Log.d(TAG, "getKatalogSuccess-code: ${response.body()}")
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

    fun getKatalogByIdSuccess(response: Response<KatalogByIdResponse>, onResult: (result: KatalogByIdResponse) -> Unit){
        Log.d(TAG, "getKatalogSuccess-code: ${response.code()}")
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : KatalogByIdResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = KatalogByIdResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : KatalogByIdResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = KatalogByIdResponse(message = messageError)
                onResult(default)
            }
        }
    }

    fun getReviewSuccess(response: Response<ReviewResponse>, onResult: (result: ReviewResponse) -> Unit){
        Log.d(TAG, "getKatalogSuccess-code: ${response.code()}")
        Log.d(TAG, "getKatalogSuccess-code: ${response.body()}")
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : ReviewResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = ReviewResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : ReviewResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = ReviewResponse(message = messageError)
                onResult(default)
            }
        }
    }

    fun uploadReviewSuccess(response: Response<UploadReviewResponse>, onResult: (result: UploadReviewResponse) -> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : UploadReviewResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = UploadReviewResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : UploadReviewResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = UploadReviewResponse(message = messageError)
                onResult(default)
            }
        }
    }
}