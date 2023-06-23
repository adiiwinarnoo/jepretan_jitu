package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class TransaksiResponse(

	@field:SerializedName("transaksi")
	val transaksi: Transaksi? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Transaksi(

	@field:SerializedName("id_product")
	val idProduct: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("bukti_pembayaran")
	val buktiPembayaran: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
