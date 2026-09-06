package com.example.app.presentation.details

import com.example.app.domain.model.ProductResponse
import com.example.app.domain.model.ProductResponseItem


sealed class UiDetailsState {
    object Loading: UiDetailsState()
    data class Success(val data: ProductResponseItem): UiDetailsState()
    data class Error(val error:String):UiDetailsState()
}