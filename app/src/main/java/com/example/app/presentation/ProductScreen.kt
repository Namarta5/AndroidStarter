package com.example.app.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.room.util.query
import coil.compose.AsyncImage

@Composable
fun ProductScreen(
    navHostController: NavHostController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

//        OutlinedTextField(
//            value = query,
//            onValueChange = {query = it
//                            viewModel.updateQuery(query)},
//            label = {Text(text = "Search Products")}
//        )
        when (val res = state) {
            ProductUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    CircularProgressIndicator()
                }
            }

            is ProductUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(res.data) { product ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable {
                                    navHostController.navigate("products/${product.id}")
                                }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                AsyncImage(model = product.image,
                                    contentDescription = null)
                                Text(text = product.id.toString())
                                Text(text = product.title)
                                Text(text = product.price.toString())
                                Text(text = product.description)
                                Text(text = product.category)
                            }
                        }
                    }

                }
            }

            is ProductUiState.Error -> {
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