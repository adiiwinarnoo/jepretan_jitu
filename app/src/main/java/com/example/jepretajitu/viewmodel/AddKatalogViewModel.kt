package com.example.jepretajitu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.AddKatalogResponse
import com.example.jepretajitu.model.RegisterResponse
import com.example.jepretajitu.repository.AddKatalogRepository
import com.example.jepretajitu.repository.RegisterRepository

class AddKatalogViewModel : ViewModel() {

    private val addKatalogRepository = AddKatalogRepository()
    var katalogData = MutableLiveData<AddKatalogResponse>()

    fun sendKatalog(idUser : Int, foto : String, fotoTwo : String, fotoThree : String,
                    judulProduct : String, nomorWhatsapp : String, deskripsi : String,
                    domisili : String, hargaProduct : String) : MutableLiveData<AddKatalogResponse>{
        addKatalogRepository.addKatalog(idUser,foto,fotoTwo,fotoThree,judulProduct,nomorWhatsapp,
        deskripsi,domisili,hargaProduct){
            katalogData.postValue(it)
        }
        return katalogData
    }
}