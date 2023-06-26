package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class ReviewResponse(

	@field:SerializedName("dataReview")
	val dataReview: List<DataReviewItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataReviewItem(

	@field:SerializedName("id_product")
	val idProduct: Int? = null,

	@field:SerializedName("review")
	val review: String? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("nama")
	val namaUser: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null
)
