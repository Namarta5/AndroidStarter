package com.example.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.model.ProductResponse
import com.example.app.domain.usecase.ProductUseCase
import com.example.app.presentation.details.UiDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val useCase: ProductUseCase) : ViewModel(){

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiDetailsState = MutableStateFlow<UiDetailsState>(UiDetailsState.Loading)
    val uiDetailsState = _uiDetailsState.asStateFlow()

    var prodcutsList = ProductResponse()
    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            try {
                useCase.execute(5).collect {
                    prodcutsList = it
                    _uiState.value = ProductUiState.Success(it)
                }
            }catch (e: Exception){
                _uiState.value = ProductUiState.Error(e?.message?:"Error")
            }

        }
    }
    public fun getProductsDetails(id:Int) {

        viewModelScope.launch {
            _uiDetailsState.value = UiDetailsState.Loading
            try {
                useCase.getProductById(id).collect {

                    _uiDetailsState.value = UiDetailsState.Success(it)
                }
            }catch (e: Exception){
                _uiDetailsState.value = UiDetailsState.Error(e?.message?:"Error")
            }

        }
    }

//    fun updateQuery(query: String) {
//        val filteredList = prodcutsList.any { it.title.contains(query, ignoreCase = true) }
//        _uiState.value = ProductUiState.Success(filteredList)}
//
//    }
}