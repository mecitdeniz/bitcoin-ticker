package com.mecitdeniz.bitcointicker.presentation

sealed class Screen(val route: String) {
    object AuthStack : Screen("auth_stack")
    object HomeStack : Screen("home_route")
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object ProfileScreen : Screen("profile_screen")
    object HomeScreen : Screen("home_screen")
}
