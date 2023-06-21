package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("dataProfile")
	val dataProfile: DataProfile? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataProfile(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("id_level")
	val idLevel: Int? = null,

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
