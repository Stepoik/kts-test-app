package ru.stepan.example.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import testapp.composeapp.generated.resources.Res
import testapp.composeapp.generated.resources.default_background
import testapp.composeapp.generated.resources.log_in
import testapp.composeapp.generated.resources.login
import testapp.composeapp.generated.resources.password

@Composable
fun LoginScreen() {
    val viewModel = viewModel { LoginViewModel(createSavedStateHandle()) }
    val state = viewModel.state.collectAsState().value
    LoginScreen(state = state, presenter = viewModel)
}

@Composable
private fun LoginScreen(state: LoginState, presenter: LoginPresenter) {
    val passwordFocusRequester = remember { FocusRequester() }
    Scaffold {
        Box {
            Image(
                painterResource(Res.drawable.default_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                Modifier.fillMaxSize().padding(it).padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = state.login,
                    onValueChange = presenter::onChangeLogin,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
                    placeholder = { Text(stringResource(Res.string.login)) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = presenter::onChangePassword,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth().focusRequester(passwordFocusRequester),
                    placeholder = { Text(stringResource(Res.string.password)) }
                )
                Button(onClick = presenter::onLogInClicked, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(Res.string.log_in))
                }
            }
        }
    }
}