package ru.stepan.reddit.onboarding.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.stepan.reddit.uikit.RedditTheme
import ru.stepan.reddit.uikit.dimens
import testapp.features.onboarding.ui.generated.resources.Res
import testapp.features.onboarding.ui.generated.resources.client_capabilities_text
import testapp.features.onboarding.ui.generated.resources.client_capabilities_title
import testapp.features.onboarding.ui.generated.resources.first
import testapp.features.onboarding.ui.generated.resources.next
import testapp.features.onboarding.ui.generated.resources.second
import testapp.features.onboarding.ui.generated.resources.start
import testapp.features.onboarding.ui.generated.resources.third
import testapp.features.onboarding.ui.generated.resources.what_can_you_do_text
import testapp.features.onboarding.ui.generated.resources.what_can_you_do_title
import testapp.features.onboarding.ui.generated.resources.what_is_reddit_text
import testapp.features.onboarding.ui.generated.resources.what_is_reddit_title

private const val PAGES_COUNT = 3

@Composable
fun AppOnboardingScreen(component: AppOnboardingComponent) {
    val pagerState = rememberPagerState { PAGES_COUNT }
    val coroutineScope = rememberCoroutineScope()
    Scaffold { innerPadding ->
        Column(
            Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = MaterialTheme.dimens.paddings.md)
                .padding(bottom = MaterialTheme.dimens.paddings.xl)
        ) {
            HorizontalPager(pagerState, userScrollEnabled = false) { page ->
                when (page) {
                    0 -> FirstPage(
                        onNext = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(1)
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )

                    1 -> SecondPage(
                        onNext = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(2)
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )

                    2 -> ThirdPage(
                        onNext = component::onStartButtonClicked,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun FirstPage(onNext: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            Modifier.weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.dimens.paddings.sm,
                alignment = Alignment.CenterVertically
            )
        ) {
            Image(
                painterResource(Res.drawable.first),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                stringResource(Res.string.what_is_reddit_title),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                stringResource(Res.string.what_is_reddit_text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                textAlign = TextAlign.Center
            )
        }
        Button(onClick = onNext) {
            Text(stringResource(Res.string.next))
        }
    }
}

@Composable
private fun SecondPage(onNext: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            Modifier.weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.dimens.paddings.sm,
                alignment = Alignment.CenterVertically
            )
        ) {
            Image(
                painterResource(Res.drawable.second),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                stringResource(Res.string.what_can_you_do_title),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                stringResource(Res.string.what_can_you_do_text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                textAlign = TextAlign.Center
            )
        }
        Button(onClick = onNext) {
            Text(stringResource(Res.string.next))
        }
    }
}

@Composable
private fun ThirdPage(onNext: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            Modifier.weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.dimens.paddings.sm,
                alignment = Alignment.CenterVertically
            )
        ) {
            Image(
                painterResource(Res.drawable.third),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                stringResource(Res.string.client_capabilities_title),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                stringResource(Res.string.client_capabilities_text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                textAlign = TextAlign.Center
            )
        }
        Button(onClick = onNext) {
            Text(stringResource(Res.string.start))
        }
    }
}

@Preview
@Composable
private fun AppOnboardingScreenPreview() {
    val component = object : AppOnboardingComponent {
        override val state: StateFlow<Unit> = MutableStateFlow(Unit)
        override val events: SharedFlow<Unit> = MutableSharedFlow()
        override fun onStartButtonClicked() {}
    }
    RedditTheme {
        AppOnboardingScreen(component)
    }
}
