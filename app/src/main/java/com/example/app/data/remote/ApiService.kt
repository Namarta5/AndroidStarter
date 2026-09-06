package com.example.app.data.remote

import com.example.app.domain.model.ProductResponse
import com.example.app.domain.model.ProductResponseItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductResponseItem
}