package com.example.app.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.app.presentation.ProductUiState
import com.example.app.presentation.ProductViewModel

@Composable
fun ProductDetailsScreen(id: String?, viewModel: ProductViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Details Page", style = MaterialTheme.typography.headlineLarge)
        Text(text = "$id", style = MaterialTheme.typography.bodyMedium)
        LaunchedEffect(id) {
            viewModel.getProductsDetails(id?.toInt() ?: 0)
        }

        val state by viewModel.uiDetailsState.collectAsState()
        when (val res = state) {
            UiDetailsState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiDetailsState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    AsyncImage(
                        model = res.data.image,
                        contentDescription = null
                    )
                    Text(text = res.data.id.toString())
                    Text(text = res.data.title)
                    Text(text = res.data.price.toString())
                    Text(text = res.data.description)
                    Text(text = res.data.category)
                }

            }

            is UiDetailsState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = res.error)
                }
            }

        }
    }
}