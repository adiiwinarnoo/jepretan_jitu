package com.example.jepretajitu.network

import com.example.jepretajitu.model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("email") nik : String,
        @Field("password") password : String) : Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/forgot-password")
    fun forgotPassword(
        @Field("email") nik : String,
        @Field("password") password : String) : Call<ForgotPasswordResponse>

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

    @FormUrlEncoded
    @POST("api/upload-katalog")
    fun uploadKatalog(
        @Field("id_user") idUser : Int,
        @Field("foto") foto : String,
        @Field("foto_two") fotoTwo : String,
        @Field("foto_three") fotoThree : String,
        @Field("judul_product") judulProduct : String,
        @Field("nomor_whatsapp") nomorWhatsapp : String,
        @Field("deskripsi") deskripsi : String,
        @Field("domisili") domisili : String,
        @Field("harga_product") hargaProduct : String
        ) : Call<AddKatalogResponse>


    @GET("api/katalog-all")
    fun getKatalogAll() : Call<DataKatalogResponse>
}