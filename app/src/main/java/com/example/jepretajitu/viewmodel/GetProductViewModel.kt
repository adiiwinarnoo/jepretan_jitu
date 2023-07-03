package com.example.jepretajitu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jepretajitu.model.GetProductResponse
import com.example.jepretajitu.repository.GetProductRepository

class GetProductViewModel : ViewModel() {

    var repoGetProduct = GetProductRepository()
    var dataGetProduct = MutableLiveData<GetProductResponse>()

    fun getProductById(idProduct : Int) : MutableLiveData<GetProductResponse>{
        repoGetProduct.getProductById(idProduct){
            dataGetProduct.postValue(it)
        }
        return dataGetProduct
    }
}