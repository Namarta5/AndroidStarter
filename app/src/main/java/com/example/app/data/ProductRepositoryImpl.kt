package com.example.app.data

import com.example.app.data.remote.ApiService
import com.example.app.domain.model.ProductResponse
import com.example.app.domain.model.ProductResponseItem
import com.example.app.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val apiService: ApiService) : ProductRepository {
    override suspend fun getProducts(limit: Int): Flow<ProductResponse> = flow {
        emit(apiService.getProducts(limit))
    }

    override suspend fun getProductById(id: Int): Flow<ProductResponseItem> = flow {
        emit(apiService.getProductById(id))
    }

}