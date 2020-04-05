package com.liverpool.app.model

import com.google.gson.annotations.SerializedName

class StatusResponse(
    @SerializedName("status") var status: String,
    @SerializedName("statusCode") var statusCode: Int
) {


}