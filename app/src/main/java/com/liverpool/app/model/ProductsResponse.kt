package com.liverpool.app.model

import com.google.gson.annotations.SerializedName

class ProductsResponse(
    @SerializedName("status") var status: StatusResponse,
    @SerializedName("plpResults") var plpResults: PLPResultsResponse
) {

}