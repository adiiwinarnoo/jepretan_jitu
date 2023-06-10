package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class DataKatalogResponse(

	@field:SerializedName("dataKatalog")
	val dataKatalog: List<DataKatalogItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataKatalogItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("judul_product")
	val judulProduct: String? = null,

	@field:SerializedName("harga_product")
	val hargaProduct: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null
)
