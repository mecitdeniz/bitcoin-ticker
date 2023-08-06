package com.mecitdeniz.bitcointicker.presentation.coin_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mecitdeniz.bitcointicker.R
import com.mecitdeniz.bitcointicker.presentation.Screen
import com.mecitdeniz.bitcointicker.presentation.coin_list.components.CoinListItem
import com.mecitdeniz.bitcointicker.presentation.components.ErrorView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListScreenViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "CoinList Screen")
                }
            )
        }
    ) { it ->
        Box(modifier = Modifier.padding(it)) {
            state.coins?.let {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(state.coins) { coin ->
                        CoinListItem(coin = coin, onClick = { id ->
                            navController.navigate(Screen.CoinDetailScreen.route + "/${id}")
                        })
                    }
                }
            }
            state.errorMessage?.let { errorMessage ->
                if (errorMessage.trim().lowercase() == "HTTP 429".trim().lowercase()) {
                    ErrorView(
                        message = context.getString(R.string.api_limit_error),
                        icon = Icons.Default.Warning
                    )
                }
            }
        }
    }
}