package com.example.jepretajitu.repository

import android.util.Log
import com.example.jepretajitu.model.*
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

    fun getPayment(result : (result : GetPaymentResponse)->Unit){
        apiConfig.server.getPayment().enqueue(object : Callback<GetPaymentResponse>{
            override fun onResponse(call: Call<GetPaymentResponse>, response: Response<GetPaymentResponse>) {
               getPaymentSuccess(response,result)
            }

            override fun onFailure(call: Call<GetPaymentResponse>, t: Throwable) {
                Log.d("PAYMENT-TRANSAKSI", "onFailure: ${t.message}")
            }

        })
    }

    fun getPaymentById(idProduct: Int,idUser: Int,result : (result : PaymentByIdResponse)->Unit){
        apiConfig.server.getPaymentById(idProduct,idUser).enqueue(object : Callback<PaymentByIdResponse>{
            override fun onResponse(call: Call<PaymentByIdResponse>, response: Response<PaymentByIdResponse>) {
               getPaymentSuccessById(response,result)
            }

            override fun onFailure(call: Call<PaymentByIdResponse>, t: Throwable) {
                Log.d("PAYMENT-TRANSAKSI", "onFailure: ${t.message}")
            }

        })
    }

    fun getPaymentByIdUser(idUser: Int,result : (result : PaymentByIdResponse)->Unit){
        apiConfig.server.getPaymentByIdUser(idUser).enqueue(object : Callback<PaymentByIdResponse>{
            override fun onResponse(call: Call<PaymentByIdResponse>, response: Response<PaymentByIdResponse>) {
                Log.d("DATA-USER-PAY", "onResponse: ${response.toString()}")
                getPaymentSuccessById(response,result)
            }

            override fun onFailure(call: Call<PaymentByIdResponse>, t: Throwable) {
                Log.d("PAYMENT-TRANSAKSI", "onFailure: ${t.message}")
            }

        })
    }

    fun getAllTransaksi(result :(result : GetTransaksiAdminResponse)-> Unit ){
        apiConfig.server.getAllTransaksi().enqueue(object : Callback<GetTransaksiAdminResponse>{
            override fun onResponse(call: Call<GetTransaksiAdminResponse>, response: Response<GetTransaksiAdminResponse>) {
                getTransaksiSucces(response,result)
            }

            override fun onFailure(call: Call<GetTransaksiAdminResponse>, t: Throwable) {
                Log.d("TRANSAKSI-FAILED", "onFailure: ${t.message}")
            }

        })
    }

    fun getPaymentForFotographer(result : (result:PaymentFotoResponse)-> Unit){
        apiConfig.server.getPaymentFotoGrapher().enqueue(object : Callback<PaymentFotoResponse>{
            override fun onResponse(call: Call<PaymentFotoResponse>, response: Response<PaymentFotoResponse>) {
                getPaymentSuccessFoto(response,result)
            }

            override fun onFailure(call: Call<PaymentFotoResponse>, t: Throwable) {
                Log.d("PAYMENT-TRANSAKSI", "onFailure: ${t.message}")
            }

        })
    }

    fun changeStatusPayment(idProduct: Int, status : String, result : (result : StatusPaymentResponse)->Unit){
        apiConfig.server.ubahPaymentStatus(idProduct, status).enqueue(object : Callback<StatusPaymentResponse>{
            override fun onResponse(call: Call<StatusPaymentResponse>, response: Response<StatusPaymentResponse>) {
                changePaymentSuccess(response,result)
            }

            override fun onFailure(call: Call<StatusPaymentResponse>, t: Throwable) {
                Log.d("PAYMENT-TRANSAKSI", "onFailure-update-payment: ${t.message}")
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

    fun getTransaksiSucces(response : Response<GetTransaksiAdminResponse>, onResult : (result : GetTransaksiAdminResponse)-> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : GetTransaksiAdminResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = GetTransaksiAdminResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : GetTransaksiAdminResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = GetTransaksiAdminResponse(message = messageError)
                onResult(default)
            }
        }
    }

    fun getPaymentSuccess(response : Response<GetPaymentResponse>, onResult : (result : GetPaymentResponse)-> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : GetPaymentResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = GetPaymentResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : GetPaymentResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = GetPaymentResponse(message = messageError)
                onResult(default)
            }
        }
    }

    fun getPaymentSuccessById(response : Response<PaymentByIdResponse>, onResult : (result : PaymentByIdResponse)-> Unit){
        Log.d("PAYMENT-TRANSAKSI", "onFailure-update-payment: ${response.body()}")
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : PaymentByIdResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = PaymentByIdResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : PaymentByIdResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = PaymentByIdResponse(message = messageError)
                onResult(default)
            }
        }
    }

    fun getPaymentSuccessFoto(response : Response<PaymentFotoResponse>, onResult : (result : PaymentFotoResponse)-> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : PaymentFotoResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = PaymentFotoResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : PaymentFotoResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = PaymentFotoResponse(message = messageError)
                onResult(default)
            }
        }
    }

    fun changePaymentSuccess(response : Response<StatusPaymentResponse>, onResult : (result : StatusPaymentResponse)-> Unit){
        when (response.code()){
            200 -> {
                onResult(response.body()!!)
            }
            400 ->{
                var default : StatusPaymentResponse? = null
                val messageError = Constant.CHECK_CONNECTION
                default = StatusPaymentResponse(message = messageError)
                onResult(default)
            }
            401 ->{
                var default : StatusPaymentResponse? = null
                val messageError = Constant.EMAIL_NOT_REGIST
                default = StatusPaymentResponse(message = messageError)
                onResult(default)
            }
        }
    }

}