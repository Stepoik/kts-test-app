package ru.stepan.example.presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import testapp.composeapp.generated.resources.Res
import testapp.composeapp.generated.resources.default_background
import testapp.composeapp.generated.resources.log_in
import testapp.composeapp.generated.resources.welcome

@Composable
fun WelcomeScreen(onLogIn: () -> Unit) {
    Scaffold { innerPadding ->
        Box {
            AsyncImage(
                "https://img.freepik.com/premium-vector/vertical-wave-gradients-captivating-designs_884160-3336.jpg?semt=ais_hybrid&w=740&q=80",
                null,
                error = painterResource(Res.drawable.default_background),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
            ) {
                Text(
                    stringResource(Res.string.welcome),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Button(onClick = onLogIn) {
                    Text(stringResource(Res.string.log_in))
                }
            }
        }
    }
}