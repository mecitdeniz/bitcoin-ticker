package com.mecitdeniz.bitcointicker.presentation

sealed class Screen(val route: String) {
    object AuthStack : Screen("auth_stack")
    object BottomNavGraph : Screen("bottom_nav_graph")
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object ProfileScreen : Screen("profile_screen")
    object CoinListScreen : Screen("coin_list_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
}
