package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class GetTransaksiAdminResponse(

	@field:SerializedName("lihatTransaksiAdmin")
	val lihatTransaksiAdmin: List<LihatTransaksiAdminItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class LihatTransaksiAdminItem(

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("judul_product")
	val judulProduct: String? = null,

	@field:SerializedName("nomor_whatsapp")
	val nomorWhatsapp: String? = null,

	@field:SerializedName("harga_product")
	val hargaProduct: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("domisili")
	val domisili: String? = null,

	@field:SerializedName("bukti_pembayaran")
	val buktiPembayaran: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("nomor_hp")
	val nomorHp: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
