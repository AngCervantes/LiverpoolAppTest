package com.liverpool.app.model

import com.google.gson.annotations.SerializedName

class RecordsResponse(
    @SerializedName("productId") var productId: String,
    @SerializedName("productDisplayName") var productDisplayName: String,
    @SerializedName("listPrice") var listPrice: Int,
    @SerializedName("smImage") var smImage: String
) {

}