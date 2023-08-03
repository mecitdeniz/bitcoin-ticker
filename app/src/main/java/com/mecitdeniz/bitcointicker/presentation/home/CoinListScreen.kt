package com.mecitdeniz.bitcointicker.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mecitdeniz.bitcointicker.presentation.home.components.CoinListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListScreenViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "CoinList Screen")
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            state.coins?.let {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(state.coins) { coin ->
                        CoinListItem(coin = coin, onClick = {

                        })
                    }
                }
            }
        }
    }
}