package com.mecitdeniz.bitcointicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.mecitdeniz.bitcointicker.presentation.Screen
import com.mecitdeniz.bitcointicker.presentation.login.LoginScreen
import com.mecitdeniz.bitcointicker.presentation.splash.SplashScreen
import com.mecitdeniz.bitcointicker.presentation.components.BottomNavGraph
import com.mecitdeniz.bitcointicker.presentation.ui.theme.BitcoinTickerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ) {
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController = navController)
                        }

                        navigation(
                            route = Screen.AuthStack.route,
                            startDestination = Screen.LoginScreen.route
                        ) {
                            composable(route = Screen.LoginScreen.route) {
                                LoginScreen(navController = navController)
                            }
                        }

                        composable(
                            route = Screen.BottomNavGraph.route,
                        ) {
                            BottomNavGraph(navController)
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