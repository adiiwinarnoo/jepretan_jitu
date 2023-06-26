package com.example.jepretajitu.network

import com.example.jepretajitu.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("email") nik : String,
        @Field("password") password : String) : Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/review")
    fun uploadReview(
        @Field("id_product") idProduct: Int,
        @Field("id_user") idUser: Int,
        @Field("review") review : String,
        @Field("rating") rating : Int) : Call<UploadReviewResponse>

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
    @POST("api/profile-update/{id}")
    fun ubahProfile(
        @Path("id") idUser : Int,
        @Field("nama") nama : String,
        @Field("email") email : String,
        @Field("nomor_hp") nomorHp : String,
        @Field("alamat") alamat : String,
        @Field("foto") foto : String? = null, ) : Call<UbahProfileResponse>

    @FormUrlEncoded
    @POST("api/payment-update/{id}")
    fun ubahPaymentStatus(
        @Path("id") idProduct: Int,
        @Field("status") status : String? = null, ) : Call<StatusPaymentResponse>

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

    @FormUrlEncoded
    @POST("api/payment")
    fun payment(
        @Field("id_user") idUser : Int,
        @Field("id_product") idProduct : Int,
        @Field("alamat") foto : String,
        @Field("tanggal") fotoTwo : String,
        @Field("bukti_pembayaran") fotoThree : String,
        @Field("status") judulProduct : String) : Call<TransaksiResponse>


    @GET("api/katalog-all")
    fun getKatalogAll() : Call<DataKatalogResponse>

    @GET("api/katalog/{id}")
    fun getKatalogById(@Path("id") idUser : Int) : Call<KatalogByIdResponse>

    @GET("api/review/{id}")
    fun getReview(@Path("id") idProduct : Int) : Call<ReviewResponse>

    @GET("api/profile/{id}")
    fun getProfile (@Path("id") idUser : Int) : Call<ProfileResponse>

    @GET("api/payment")
    fun getPayment() : Call<GetPaymentResponse>

    @GET("api/payment/fotographer")
    fun getPaymentFotoGrapher() : Call<PaymentFotoResponse>

    @GET("api/payment/{id}/{id_user}")
    fun getPaymentById(@Path("id") idProduct : Int,@Path("id_user") idUser: Int) : Call<PaymentByIdResponse>
}