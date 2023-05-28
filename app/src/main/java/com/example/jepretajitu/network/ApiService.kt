package com.example.jepretajitu.network

import com.example.jepretajitu.model.LoginResponse
import com.example.jepretajitu.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("email") nik : String,
        @Field("password") password : String) : Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/register")
    fun register(
        @Field("nama") nama : String,
        @Field("email") email : String,
        @Field("nomor_hp") nomorHp : String,
        @Field("password") password : String,
        @Field("id_level") id_level : Int,
        @Field("foto") foto : String? = null,
        @Field("alamat") alamat : String,) : Call<RegisterResponse>
}