package com.example.jepretajitu.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.GetPaymentResponse
import com.example.jepretajitu.model.StatusPaymentResponse
import com.example.jepretajitu.model.TransaksiResponse
import com.example.jepretajitu.repository.TransaksiRepository

class TransaksiViewModel : ViewModel() {

    var transaksiRepo = TransaksiRepository()
    var dataTransaksi = MutableLiveData<TransaksiResponse>()
    var dataGetPayment = MutableLiveData<GetPaymentResponse>()
    var dataUpdateStatus = MutableLiveData<StatusPaymentResponse>()

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

    fun changePayment(idProduct: Int, status: String) : MutableLiveData<StatusPaymentResponse>{
        transaksiRepo.changeStatusPayment(idProduct,status){
            dataUpdateStatus.postValue(it)
            getPayment()
        }
        return dataUpdateStatus
    }

}