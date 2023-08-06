package com.mecitdeniz.bitcointicker.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mecitdeniz.bitcointicker.common.Constants
import com.mecitdeniz.bitcointicker.common.Utils
import com.mecitdeniz.bitcointicker.presentation.coin_detail.components.CoinDetailActionCard
import com.mecitdeniz.bitcointicker.presentation.coin_detail.components.CoinDetailDescriptionCard
import com.mecitdeniz.bitcointicker.presentation.coin_detail.components.CoinDetailHeader

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        state.coin?.let { coin ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
            ) {

                CoinDetailHeader(coin = coin)

                state.refreshInterval?.let { refreshInterval ->
                    CoinDetailActionCard(
                        selectedOption = Utils.getIntervalString(refreshInterval),
                        options = Constants.REFRESH_INTERVAL.map {
                            it.first
                        },
                        onOptionSelected = {
                            viewModel.setIntervalValue(it)
                        },
                        isInFavorites = state.isInFavorites,
                        onIconClick = {
                            viewModel.onFavoritesButtonClick()
                        }
                    )
                }

                coin.description?.let {
                    CoinDetailDescriptionCard(it)
                }
            }
        }

    }
}