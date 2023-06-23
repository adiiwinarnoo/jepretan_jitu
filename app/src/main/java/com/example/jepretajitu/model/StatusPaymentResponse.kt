package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class StatusPaymentResponse(

	@field:SerializedName("statusChange")
	val statusChange: StatusChange? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class StatusChange(

	@field:SerializedName("id_product")
	val idProduct: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("bukti_pembayaran")
	val buktiPembayaran: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
