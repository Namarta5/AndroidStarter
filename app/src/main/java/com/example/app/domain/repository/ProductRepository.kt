package com.example.app.domain.repository

import com.example.app.domain.model.ProductResponse
import com.example.app.domain.model.ProductResponseItem
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(limit:Int) : Flow<ProductResponse>
    suspend fun getProductById(id:Int) : Flow<ProductResponseItem>
}