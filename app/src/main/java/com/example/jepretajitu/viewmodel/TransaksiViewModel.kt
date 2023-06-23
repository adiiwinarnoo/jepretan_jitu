package com.example.jepretajitu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.TransaksiResponse
import com.example.jepretajitu.repository.TransaksiRepository

class TransaksiViewModel : ViewModel() {

    var transaksiRepo = TransaksiRepository()
    var dataTransaksi = MutableLiveData<TransaksiResponse>()

    fun sendPayment(idUser : Int, idProduct : Int, alamat : String, tanggal : String,
                    buktiPembayaran : String, status : String) : MutableLiveData<TransaksiResponse>{
        transaksiRepo.uploadPayment(idUser,idProduct,alamat,tanggal,buktiPembayaran,status){
            dataTransaksi.postValue(it)
        }
        return dataTransaksi
    }

}