package ru.stepan.reddit.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import dev.lokksmith.client.request.flow.AuthFlowUserAgentResponseHandler
import dev.lokksmith.compose.AuthFlowLauncher
import dev.lokksmith.compose.rememberAuthFlowLauncher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.uikit.RedditTheme
import ru.stepan.reddit.uikit.components.RedditOutlinedTextField
import ru.stepan.reddit.uikit.dimens
import testapp.features.auth.ui.generated.resources.Res
import testapp.features.auth.ui.generated.resources.incorrect_credential
import testapp.features.auth.ui.generated.resources.login
import testapp.features.auth.ui.generated.resources.network_error
import testapp.features.auth.ui.generated.resources.password
import testapp.features.auth.ui.generated.resources.sign_in
import testapp.features.auth.ui.generated.resources.unknown_error

@Composable
internal fun LoginScreen(component: LoginComponent) {
    val state = component.state.collectAsState().value
    val authLauncher = rememberAuthFlowLauncher()
    LaunchedEffect(Unit) {
        component.events.collect { event ->
            when (event) {
                is LoginScreenEvent.AuthFlowInitiation -> {
                    authLauncher.launch(
                        initiation = event.initiation
                    )
                }
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.dimens.paddings.md),
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.dimens.paddings.md,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = component::onButtonClicked,
                enabled = state.isButtonEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.sign_in))
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    val component = object : LoginComponent {
        override val onAuthorized: () -> Unit = {}
        override val events: SharedFlow<LoginScreenEvent> = MutableSharedFlow()
        override val state: StateFlow<LoginScreenState> = MutableStateFlow(LoginScreenState())
        override fun onButtonClicked() {}
    }
    RedditTheme {
        LoginScreen(component)
    }
}