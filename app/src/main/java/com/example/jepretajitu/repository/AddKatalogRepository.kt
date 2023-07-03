package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.AddKatalogResponse
import com.example.jepretajitu.model.LoginResponse
import com.example.jepretajitu.model.UpdateProductResponse
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddKatalogRepository {

    private val apiConfig = ApiConfig()

    fun addKatalog(idUser : Int, foto : String, fotoTwo : String, fotoThree : String,
                   judulProduct : String, nomorWhatsapp : String, deskripsi : String,
                   domisili : String, hargaProduct : String, result : (result : AddKatalogResponse) -> Unit) {
        apiConfig.server.uploadKatalog(idUser,foto,fotoTwo,fotoThree,judulProduct,nomorWhatsapp,
            deskripsi,domisili,hargaProduct).enqueue(object : Callback<AddKatalogResponse>{
            override fun onResponse(call: Call<AddKatalogResponse>, response: Response<AddKatalogResponse>) {
                addKatalogSuccess(response,result)
            }

            override fun onFailure(call: Call<AddKatalogResponse>, t: Throwable) {
                Log.d("UPLOAD-KATALOG-FAILED", "onFailure: ${t.message}, id user $idUser")
            }
        })
    }

    fun updateKatalog(idProduct : Int, foto : String, fotoTwo : String, fotoThree : String,
                      judulProduct : String, nomorWhatsapp : String, deskripsi : String,
                      domisili : String, hargaProduct : String, result : (result : UpdateProductResponse) -> Unit){
        apiConfig.server.updateKatalog(idProduct,foto,fotoTwo,fotoThree,judulProduct,nomorWhatsapp,
            deskripsi,domisili,hargaProduct).enqueue(object : Callback<UpdateProductResponse>{
            override fun onResponse(call: Call<UpdateProductResponse>, response: Response<UpdateProductResponse>) {
                Log.d("UPLOAD-KATALOG-FAILED", "onResponse: $response")
                updateKatalogSuccess(response,result)
            }

            override fun onFailure(call: Call<UpdateProductResponse>, t: Throwable) {
                Log.d("UPLOAD-KATALOG-FAILED", "onFailure: ${t.message}, id user $idProduct")
            }
        })
    }


    fun addKatalogSuccess(response: Response<AddKatalogResponse>, onResult: (result : AddKatalogResponse) -> Unit){
        Log.d("UPLOAD-KATALOG-FAILED", "addKatalogSuccess: ${response.code()}")
        Log.d("UPLOAD-KATALOG-FAILED", "addKatalogSuccess: ${response.body()}")
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : AddKatalogResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = AddKatalogResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : AddKatalogResponse? = null
                val messageError = Constant.DATA_EMPTY
                default = AddKatalogResponse(message = messageError)
                onResult(default)
            }
        }
    }
    fun updateKatalogSuccess(response: Response<UpdateProductResponse>, onResult: (result : UpdateProductResponse) -> Unit){
        Log.d("UPLOAD-KATALOG-FAILED", "addKatalogSuccess: ${response.code()}")
        Log.d("UPLOAD-KATALOG-FAILED", "addKatalogSuccess: ${response.body()}")
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : UpdateProductResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = UpdateProductResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : UpdateProductResponse? = null
                val messageError = Constant.DATA_EMPTY
                default = UpdateProductResponse(message = messageError)
                onResult(default)
            }
        }
    }
}