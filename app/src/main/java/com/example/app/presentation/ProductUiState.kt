package com.example.app.presentation

import com.example.app.domain.model.ProductResponse

sealed class ProductUiState {
object Loading: ProductUiState()
    data class Success(val data: ProductResponse): ProductUiState()
    data class Error(val error:String):ProductUiState()
}