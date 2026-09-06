package com.example.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.navigation.compose.rememberNavController
import com.example.app.presentation.ProductScreen
import com.example.app.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(android.R.layout.simple_list_item_1)
        setContent {
            val navController = rememberNavController()
            AppNavigation(navController)
        }
    }
}