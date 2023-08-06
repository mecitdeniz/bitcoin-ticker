package com.mecitdeniz.bitcointicker.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mecitdeniz.bitcointicker.presentation.Screen
import com.mecitdeniz.bitcointicker.presentation.coin_detail.CoinDetailScreen
import com.mecitdeniz.bitcointicker.presentation.my_coins.MyCoinsScreen

@Composable
fun MyCoinsStack() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MyCoinsScreen.route
    ) {
        composable(
            route = Screen.MyCoinsScreen.route,
        ) {
            MyCoinsScreen(navController = navController)
        }

        composable(
            route = Screen.CoinDetailScreen.route + "/{coinId}",
        ) {
            CoinDetailScreen()
        }
    }
}