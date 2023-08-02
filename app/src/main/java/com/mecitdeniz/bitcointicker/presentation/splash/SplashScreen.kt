package com.mecitdeniz.bitcointicker.presentation.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mecitdeniz.bitcointicker.R
import com.mecitdeniz.bitcointicker.domain.FirebaseAuthService
import com.mecitdeniz.bitcointicker.presentation.components.AppLogo

@Composable
fun SplashScreen(
    navController: NavController
) {

    val context = LocalContext.current
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

        //TODO: change usage of FirebaseAuthService
        if (!FirebaseAuthService().isUserLoggedIn()) {
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