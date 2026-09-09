package com.littleapp.stockmarket.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.littleapp.stockmarket.ui.CompanyListingsScreen
import com.littleapp.stockmarket.viewmodel.StockMarketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            StockMarketNavHost()
        }
    }
}

@Composable
fun StockMarketNavHost() {
    val viewModel: StockMarketViewModel = hiltViewModel()

    CompanyListingsScreen(
        viewModel = viewModel
    )
}