package com.mecitdeniz.bitcointicker.presentation.components

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mecitdeniz.bitcointicker.BottomBarScreen
import com.mecitdeniz.bitcointicker.presentation.Screen
import com.mecitdeniz.bitcointicker.presentation.profile.ProfileScreen

@Composable
fun BottomNavGraph(
    rootNavController: NavController
) {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            navController = navController,
            route = Screen.BottomNavGraph.route,
            startDestination = BottomBarScreen.Profile.route,
            enterTransition = {
                fadeIn()
            }
        ) {
            composable(
                route = BottomBarScreen.Home.route,
            ) {
                CoinStack()
            }

            composable(
                route = BottomBarScreen.Favorites.route,
            ) {
                MyCoinsStack()
            }

            composable(
                route = BottomBarScreen.Profile.route,
            ) {
                ProfileScreen(navController = rootNavController)
            }
        }
    }
}