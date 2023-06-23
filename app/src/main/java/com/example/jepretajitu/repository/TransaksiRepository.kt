package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.DataKatalogResponse
import com.example.jepretajitu.model.TransaksiResponse
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransaksiRepository {

    var apiConfig = ApiConfig()

    fun uploadPayment(idUser : Int, idProduct : Int, alamat : String, tanggal : String,
                      buktiPembayaran : String, status : String, result : (result : TransaksiResponse)->Unit){
        apiConfig.server.payment(idUser,idProduct,alamat,tanggal,buktiPembayaran,status).enqueue(object : Callback<TransaksiResponse>{
            override fun onResponse(call: Call<TransaksiResponse>, response: Response<TransaksiResponse>) {
                paymentSuccess(response, onResult = result)
            }

            override fun onFailure(call: Call<TransaksiResponse>, t: Throwable) {
                Log.d("PAYMENT-TRANSAKSI", "onFailure: ${t.message}")
            }

        })
    }

    fun paymentSuccess(response : Response<TransaksiResponse>, onResult : (result : TransaksiResponse)-> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : TransaksiResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = TransaksiResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : TransaksiResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = TransaksiResponse(message = messageError)
                onResult(default)
            }
        }
    }

}