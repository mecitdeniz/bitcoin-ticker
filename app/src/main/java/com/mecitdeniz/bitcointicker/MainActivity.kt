package com.mecitdeniz.bitcointicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.mecitdeniz.bitcointicker.presentation.profile.ProfileScreen
import com.mecitdeniz.bitcointicker.presentation.login.LoginScreen
import com.mecitdeniz.bitcointicker.presentation.login.LoginScreenViewModel
import com.mecitdeniz.bitcointicker.presentation.splash.SplashScreen
import com.mecitdeniz.bitcointicker.presentation.home.HomeScreen
import com.mecitdeniz.bitcointicker.presentation.profile.ProfileScreenViewModel
import com.mecitdeniz.bitcointicker.presentation.ui.theme.BitcoinTickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitcoinTickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "splash") {
                        composable(route = "splash") {
                            SplashScreen(navController = navController)
                        }

                        navigation(
                            route = "auth",
                            startDestination = "login"
                        ) {
                            composable(route = "login") {
                                val viewModel = viewModel<LoginScreenViewModel>()
                                LoginScreen(navController = navController, viewModel = viewModel)
                            }
                        }

                        navigation(
                            route = "feed",
                            startDestination = "home"
                        ) {
                            composable(route = "home") {
                                HomeScreen(navController = navController)
                            }

                            composable(route = "profile") {
                                val viewModel = viewModel<ProfileScreenViewModel>()
                                ProfileScreen(
                                    navController = navController,
                                    viewModel = viewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BitcoinTickerTheme {

    }
}