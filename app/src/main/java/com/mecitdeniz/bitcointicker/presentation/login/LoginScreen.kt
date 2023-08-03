package com.mecitdeniz.bitcointicker.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mecitdeniz.bitcointicker.R
import com.mecitdeniz.bitcointicker.presentation.Screen
import com.mecitdeniz.bitcointicker.presentation.components.AppLogo
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val state = viewModel.state.value
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                LoginScreenViewModel.UiEvent.CreateAccountSuccess,
                LoginScreenViewModel.UiEvent.LoginSuccess -> {
                    navController.navigate(Screen.HomeStack.route) {
                        popUpTo(Screen.AuthStack.route) {
                            inclusive = true
                        }
                    }
                }
                is LoginScreenViewModel.UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(horizontal = 16.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AppLogo()

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.email,
                singleLine = true,
                isError = state.emailError?.isNotEmpty() ?: false,
                supportingText = {
                    state.emailError?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.error)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = {
                    Text(text = context.getString(R.string.email))
                },
                onValueChange = { email ->
                    viewModel.onEvent(LoginScreenEvent.OnEmailChanged(email))
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                singleLine = true,
                isError = state.passwordError?.isNotEmpty() ?: false,
                supportingText = {
                    state.passwordError?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.error)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                label = {
                    Text(text = context.getString(R.string.password))
                },
                placeholder = {
                    Text(text = context.getString(R.string.password))
                },
                onValueChange = { password ->
                    viewModel.onEvent(LoginScreenEvent.OnPasswordChanged(password))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
                colors = ButtonColors(
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
                    disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                ),
                onClick = {
                    viewModel.onEvent(LoginScreenEvent.SignIn)
                }) {
                Text(text = context.getString(R.string.sing_in))
            }


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.tertiaryContainer
                )
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = context.getString(R.string.do_not_have_an_account),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.tertiaryContainer
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                enabled = true,
                colors = ButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                ),
                onClick = {
                    viewModel.onEvent(LoginScreenEvent.CreateAccount)
                }) {
                Text(text = context.getString(R.string.create_account))
            }
        }
    }
}
