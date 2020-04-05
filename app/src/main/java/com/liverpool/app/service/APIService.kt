package com.liverpool.app.service

import com.liverpool.app.model.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    fun getProductsByName(@Url url: String): Call<ProductsResponse>
}