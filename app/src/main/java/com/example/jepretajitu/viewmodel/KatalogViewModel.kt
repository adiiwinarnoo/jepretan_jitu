package com.example.jepretajitu.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.*
import com.example.jepretajitu.repository.LihatKatalogRepository
import com.example.jepretajitu.repository.LoginRepository

class KatalogViewModel : ViewModel() {

    private val katalogRepository = LihatKatalogRepository()
    var katalogData = MutableLiveData<DataKatalogResponse>()
    var katalogByIdData = MutableLiveData<KatalogByIdResponse>()
    var reviewData = MutableLiveData<ReviewResponse>()
    var uploadReviewData = MutableLiveData<UploadReviewResponse>()

    fun getKatalogAll() : MutableLiveData<DataKatalogResponse>{
        katalogRepository.getKatalogAll {
            Log.d("KATALOG-", "getKatalogAll: ${it.dataKatalog}")
            katalogData.postValue(it)
        }
        return katalogData
    }

    fun getKatalogById(idUser : Int) : MutableLiveData<KatalogByIdResponse>{
        katalogRepository.getKatalogById(idUser){
            katalogByIdData.postValue(it)
        }
        return katalogByIdData
    }

    fun getReviewById(idProduct : Int) : MutableLiveData<ReviewResponse>{
        katalogRepository.getReview(idProduct){
            reviewData.postValue(it)
        }
        return reviewData
    }

    fun uploadReview(idProduct : Int,idUser: Int,review : String, rating : Int) : MutableLiveData<UploadReviewResponse>{
        katalogRepository.uploadReview(idProduct,idUser,review,rating){
            uploadReviewData.postValue(it)
        }
        return uploadReviewData
    }
}