package ru.stepan.example

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.stepan.example.presentation.Navigation
import ru.stepan.example.presentation.login.LoginScreen
import ru.stepan.example.presentation.welcome.WelcomeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navigationController = rememberNavController()
        NavHost(navController = navigationController, startDestination = Navigation.Welcome) {
            composable<Navigation.Welcome> {
                WelcomeScreen(onLogIn = { navigationController.navigate(Navigation.Login) })
            }

            composable<Navigation.Login> {
                LoginScreen()
            }
        }
    }
}