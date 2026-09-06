package com.example.app.domain.usecase

import com.example.app.domain.repository.ProductRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(private val repository: ProductRepository){
    suspend fun execute(limit:Int) = repository.getProducts(limit)
    suspend fun getProductById(id:Int) = repository.getProductById(id)
}