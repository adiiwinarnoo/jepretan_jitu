package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("dataLogin")
	val dataLogin: DataLogin? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataLogin(

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("id_level")
	val idLevel: Int? = null,

	@field:SerializedName("telepon")
	val telepon: Long? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("nomor_hp")
	val nomorHp: String? = null,
)
