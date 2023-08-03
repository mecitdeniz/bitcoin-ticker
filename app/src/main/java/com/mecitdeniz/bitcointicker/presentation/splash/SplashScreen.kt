package com.mecitdeniz.bitcointicker.presentation.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mecitdeniz.bitcointicker.R
import com.mecitdeniz.bitcointicker.presentation.components.AppLogo
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is SplashScreenViewModel.UiEvent.LoginStatus -> {
                    if (!event.isLoggedIn) {
                        navController.navigate("auth") {
                            popUpTo("splash") {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate("feed") {
                            popUpTo("splash") {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogo()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = context.getString(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}