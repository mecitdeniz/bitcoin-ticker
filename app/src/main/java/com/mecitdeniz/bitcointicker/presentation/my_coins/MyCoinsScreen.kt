package com.mecitdeniz.bitcointicker.presentation.my_coins

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mecitdeniz.bitcointicker.domain.model.Coin
import com.mecitdeniz.bitcointicker.domain.model.CoinDetail
import com.mecitdeniz.bitcointicker.presentation.Screen
import com.mecitdeniz.bitcointicker.presentation.coin_list.components.CoinListItem
import com.mecitdeniz.bitcointicker.presentation.components.ErrorView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCoinsScreen(
    viewModel: MyCoinsScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Coins")
                }
            )
        }) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            state.coins?.let {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(state.coins) { coin ->
                        CoinListItem(
                            coin = coin.toCoin(),
                            onClick = { id ->
                                navController.navigate(Screen.CoinDetailScreen.route + "/${id}")
                            },
                            onDeleteClick = {
                                viewModel.removeFromFavorites(it)
                            },
                            showDeleteIcon = true
                        )
                    }
                }
            }
            state.errorMessage?.let { errorMessage ->
                ErrorView(
                    message = errorMessage,
                    icon = Icons.Default.Warning
                )
            }
        }
    }
}

fun CoinDetail.toCoin(): Coin {
    return Coin(
        id = id,
        symbol = symbol,
        name = name,
        image = image,
        currentPrice = currentPrice ?: 0.0,
        marketCapRank = marketCapRank,
        priceChangePercentage24H = priceChangePercentage24H,
        firebaseId = firebaseId.toString()
    )
}