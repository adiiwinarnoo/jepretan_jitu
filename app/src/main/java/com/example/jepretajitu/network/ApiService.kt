package com.example.jepretajitu.network

import com.example.jepretajitu.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("nik") nik : String,
        @Field("password") password : String) : Call<LoginResponse>
}