package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class GetProductResponse(

	@field:SerializedName("dataProductById")
	val dataProductById: List<DataProductByIdItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataProductByIdItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("judul_product")
	val judulProduct: String? = null,

	@field:SerializedName("foto_two")
	val fotoTwo: String? = null,

	@field:SerializedName("nomor_whatsapp")
	val nomorWhatsapp: String? = null,

	@field:SerializedName("foto_three")
	val fotoThree: String? = null,

	@field:SerializedName("harga_product")
	val hargaProduct: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("domisili")
	val domisili: String? = null
)
