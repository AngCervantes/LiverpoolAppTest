package com.liverpool.app.model

import com.google.gson.annotations.SerializedName

class PLPResultsResponse (
    @SerializedName("records") var records: ArrayList<RecordsResponse>
) {

}