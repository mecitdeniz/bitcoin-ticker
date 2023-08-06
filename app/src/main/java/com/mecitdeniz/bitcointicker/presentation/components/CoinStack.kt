package com.mecitdeniz.bitcointicker.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mecitdeniz.bitcointicker.presentation.Screen
import com.mecitdeniz.bitcointicker.presentation.coin_detail.CoinDetailScreen
import com.mecitdeniz.bitcointicker.presentation.coin_list.CoinListScreen

@Composable
fun CoinStack() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.CoinListScreen.route
    ) {
        composable(route = Screen.CoinListScreen.route) {
            CoinListScreen(navController = navController)
        }

        composable(route = Screen.CoinDetailScreen.route + "/{coinId}") {
            CoinDetailScreen()
        }
    }
}