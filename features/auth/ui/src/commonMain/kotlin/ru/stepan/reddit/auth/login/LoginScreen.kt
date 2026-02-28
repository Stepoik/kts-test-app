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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.stepan.reddit.uikit.components.RedditOutlinedTextField
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

    LaunchedEffect(Unit) {
        component.events.collect {
            when (it) {
                is LoginScreenEvent.Authorized -> component.onAuthorized() // По факту вообще не нужно, сделал чисто чтоб как в дз было
            }
        }
    }

    val focusRequester = remember { FocusRequester() }

    Scaffold {
        Column(
            Modifier.padding(it).fillMaxSize().padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(
                10.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(Res.string.sign_in),
                style = MaterialTheme.typography.titleMedium
            )
            RedditOutlinedTextField(
                value = state.username,
                onValueChange = component::onUsernameChanged,
                placeholder = stringResource(Res.string.login),
                keyboardActions = KeyboardActions(onNext = { focusRequester.requestFocus() }),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            RedditOutlinedTextField(
                value = state.password,
                onValueChange = component::onPasswordChanged,
                placeholder = stringResource(Res.string.password),
                keyboardActions = KeyboardActions(onDone = { component.onButtonClicked() }),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth().focusRequester(focusRequester)
            )
            if (state.error != null) {
                Text(
                    state.error.toText(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
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

@Composable
private fun LoginScreenError.toText(): String {
    val res = when (this) {
        LoginScreenError.INCORRECT_CREDENTIALS -> Res.string.incorrect_credential
        LoginScreenError.NETWORK -> Res.string.network_error
        LoginScreenError.UNKNOWN -> Res.string.unknown_error
    }
    return stringResource(res)
}