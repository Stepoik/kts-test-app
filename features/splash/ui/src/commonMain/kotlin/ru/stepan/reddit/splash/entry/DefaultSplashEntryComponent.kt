package ru.stepan.reddit.splash.entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.auth.api.AccountRepository
import testapp.features.splash.ui.generated.resources.Res
import testapp.features.splash.ui.generated.resources.reddit

class DefaultSplashEntryComponent(
    componentContext: ComponentContext,
    private val onNavigateHome: () -> Unit,
    private val onNavigateAuth: () -> Unit,
    private val accountRepository: AccountRepository
) : SplashEntryComponent(componentContext) {

    init {
        componentScope.launch {
            accountRepository.getMe()
                .onSuccess {
                    onNavigateHome()
                }.onFailure {
                    if (accountRepository.activeAccount.first() == null) {
                        onNavigateAuth()
                    } else {
                        onNavigateHome()
                    }
                }
        }
    }

    @Composable
    override fun Render() {
        Scaffold {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painterResource(Res.drawable.reddit),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            }
        }
    }

    class Factory : SplashEntryComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            onNavigateHome: () -> Unit,
            onNavigateAuth: () -> Unit
        ): SplashEntryComponent {
            return getKoin().get { parametersOf(componentContext, onNavigateHome, onNavigateAuth) }
        }
    }
}