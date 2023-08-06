package com.mecitdeniz.bitcointicker.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mecitdeniz.bitcointicker.R
import com.mecitdeniz.bitcointicker.presentation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                ProfileScreenViewModel.UiEvent.SignOut -> {
                    navController.navigate(Screen.AuthStack.route) {
                        popUpTo(Screen.BottomNavGraph.route) {
                            inclusive = true
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
        state?.email?.let { email ->
            Text(text = context.getString(R.string.logged_in_as))

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = email, style = MaterialTheme.typography.labelLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                viewModel.signOut()
            }) {
                Text(text = context.getString(R.string.log_out))
            }
        }
    }
}