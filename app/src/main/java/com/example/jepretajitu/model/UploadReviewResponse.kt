package com.example.jepretajitu.model

import com.google.gson.annotations.SerializedName

data class UploadReviewResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("dataDetailProductReview")
	val dataDetailProductReview: DataDetailProductReview? = null
)

data class DataDetailProductReview(

	@field:SerializedName("id_product")
	val idProduct: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("review")
	val review: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
