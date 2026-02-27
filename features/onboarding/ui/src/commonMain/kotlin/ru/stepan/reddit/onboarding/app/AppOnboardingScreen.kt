package ru.stepan.reddit.onboarding.app

import androidx.compose.foundation.Image
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
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import testapp.features.onboarding.ui.generated.resources.Res
import testapp.features.onboarding.ui.generated.resources.client_capabilities_text
import testapp.features.onboarding.ui.generated.resources.client_capabilities_title
import testapp.features.onboarding.ui.generated.resources.first
import testapp.features.onboarding.ui.generated.resources.next
import testapp.features.onboarding.ui.generated.resources.second
import testapp.features.onboarding.ui.generated.resources.start
import testapp.features.onboarding.ui.generated.resources.what_can_you_do_text
import testapp.features.onboarding.ui.generated.resources.what_can_you_do_title
import testapp.features.onboarding.ui.generated.resources.what_is_reddit_text
import testapp.features.onboarding.ui.generated.resources.what_is_reddit_title

private const val PAGES_COUNT = 3

@Composable
fun AppOnboardingScreen(component: AppOnboardingComponent) {
    val pagerState = rememberPagerState { PAGES_COUNT }
    val coroutineScope = rememberCoroutineScope()
    Scaffold {
        Column(Modifier.fillMaxSize().padding(it)) {
            HorizontalPager(pagerState, userScrollEnabled = false) {
                when (it) {
                    0 -> FirstPage(onNext = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(1)
                        }
                    }, modifier = Modifier.fillMaxSize())

                    1 -> SecondPage(onNext = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(2)
                        }
                    }, modifier = Modifier.fillMaxSize())

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
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
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
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
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
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
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
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
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
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            Image(
                painterResource(Res.drawable.second),
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
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
            )
        }
        Button(onClick = onNext) {
            Text(stringResource(Res.string.start))
        }
    }
}
