package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class AddKatalogResponse(

	@field:SerializedName("dataUser")
	val dataUser: DataUser? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataUser(

	@field:SerializedName("judul_product")
	val judulProduct: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("nomor_whatsapp")
	val nomorWhatsapp: String? = null,

	@field:SerializedName("foto_two")
	val fotoTwo: String? = null,

	@field:SerializedName("foto_three")
	val fotoThree: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("harga_product")
	val hargaProduct: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("domisili")
	val domisili: String? = null
)