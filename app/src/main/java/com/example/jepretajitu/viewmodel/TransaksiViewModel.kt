package com.example.jepretajitu.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.*
import com.example.jepretajitu.repository.TransaksiRepository

class TransaksiViewModel : ViewModel() {

    var transaksiRepo = TransaksiRepository()
    var dataTransaksi = MutableLiveData<TransaksiResponse>()
    var dataGetPayment = MutableLiveData<GetPaymentResponse>()
    var dataUpdateStatus = MutableLiveData<StatusPaymentResponse>()
    var fotoGrapherData = MutableLiveData<PaymentFotoResponse>()
    var paymentById = MutableLiveData<PaymentByIdResponse>()
    var paymentByIdUser = MutableLiveData<PaymentByIdResponse>()
    var adminData = MutableLiveData<GetTransaksiAdminResponse>()

    fun sendPayment(idUser : Int, idProduct : Int, alamat : String, tanggal : String,
                    buktiPembayaran : String, status : String) : MutableLiveData<TransaksiResponse>{
        transaksiRepo.uploadPayment(idUser,idProduct,alamat,tanggal,buktiPembayaran,status){
            dataTransaksi.postValue(it)
        }
        return dataTransaksi
    }

    fun getPayment() : MutableLiveData<GetPaymentResponse>{
        transaksiRepo.getPayment {
            dataGetPayment.postValue(it)
        }
        return dataGetPayment
    }
    fun getPaymentById(idProduct: Int,idUser: Int) : MutableLiveData<PaymentByIdResponse>{
        transaksiRepo.getPaymentById(idProduct,idUser) {
            paymentById.postValue(it)
        }
        return paymentById
    }
    fun getPaymentByIdUser(idUser: Int) : MutableLiveData<PaymentByIdResponse>{
        transaksiRepo.getPaymentByIdUser(idUser) {
            Log.d("DATA-USER-PAY", "onResponse: ${it.toString()}")
            paymentByIdUser.postValue(it)
        }
        return paymentByIdUser
    }

    fun getFotoGrapherPayment() : MutableLiveData<PaymentFotoResponse>{
        transaksiRepo.getPaymentForFotographer {
            fotoGrapherData.postValue(it)
        }
        return fotoGrapherData
    }

    fun changePayment(idProduct: Int, status: String) : MutableLiveData<StatusPaymentResponse>{
        transaksiRepo.changeStatusPayment(idProduct,status){
            dataUpdateStatus.postValue(it)
            getPayment()
        }
        return dataUpdateStatus
    }

    fun getAllTransaksi() : MutableLiveData<GetTransaksiAdminResponse>{
        transaksiRepo.getAllTransaksi {
            adminData.postValue(it)
        }
        return adminData
    }

}