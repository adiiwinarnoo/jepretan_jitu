package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class GetPaymentResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("dataPayment")
	val dataPayment: List<DataPaymentItem?>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataPaymentItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("judul_product")
	val judulProduct: String? = null,

	@field:SerializedName("harga_product")
	val hargaProduct: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("bukti_pembayaran")
	val buktiPembayaran: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("status")
	var status: String? = null,

	@field:SerializedName("nomor_hp")
	var nomorHpUser : String? =null,

	@field:SerializedName("nomor_whatsapp")
	var nomorWhatsapp : String? =null,

)
