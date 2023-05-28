package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("dataUser")
	val dataRegister: DataRegister? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataRegister(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("id_level")
	val idLevel: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("nomor_hp")
	val nomorHp: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
