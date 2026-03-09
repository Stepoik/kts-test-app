package ru.stepan.reddit.home.entry

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.course.details.entry.CourseDetailsEntryComponent
import ru.stepan.reddit.recommendations.entry.RecommendationsEntryComponent
import ru.stepan.reddit.search.entry.SearchEntryComponent
import ru.stepan.reddit.uikit.icons.Home
import ru.stepan.reddit.uikit.icons.Icons
import ru.stepan.reddit.uikit.icons.Search

class DefaultHomeEntryComponent(
    componentContext: ComponentContext,
    private val recommendationsComponentFactory: RecommendationsEntryComponent.Factory,
    private val searchComponentFactory: SearchEntryComponent.Factory,
    private val courseDetailsComponentFactory: CourseDetailsEntryComponent.Factory
) : HomeEntryComponent(componentContext) {
    private val navigation = StackNavigation<Config>()
    private val stack = childStack(
        source = navigation,
        initialConfiguration = Config.Recommendations,
        handleBackButton = true,
        serializer = Config.serializer(),
        childFactory = ::createChild
    )

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            is Config.Recommendations -> Child.Recommendations(
                recommendationsComponentFactory.create(
                    componentContext = componentContext,
                    onSelectCourse = {
                        navigation.pushNew(Config.CourseDetails(it))
                    }
                )
            )

            is Config.Search -> Child.Search(
                searchComponentFactory.create(
                    componentContext = componentContext,
                    onSelectCourse = {
                        navigation.pushNew(Config.CourseDetails(it))
                    }
                )
            )

            is Config.CourseDetails -> Child.CourseDetails(
                component = courseDetailsComponentFactory.create(
                    componentContext = componentContext,
                    courseId = config.courseId
                )
            )
        }
    }

    @Composable
    override fun Render() {
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    onNavigateSearch = { navigation.pushToFront(Config.Search) },
                    onNavigateRecommendations = { navigation.pushToFront(Config.Recommendations) },
                    stack = stack
                )
            }
        ) { paddingValues ->
            Children(
                stack,
                modifier = Modifier.paddingBottomWithConsume(paddingValues)
            ) {
                when (val instance = it.instance) {
                    is Child.Recommendations -> instance.component.Render()
                    is Child.Search -> instance.component.Render()
                    is Child.CourseDetails -> instance.component.Render()
                }
            }
        }
    }

    @Serializable
    private sealed class Config {
        @Serializable
        object Recommendations : Config()

        @Serializable
        object Search : Config()

        @Serializable
        data class CourseDetails(val courseId: Long) : Config()
    }

    sealed class Child {
        class Recommendations(val component: RecommendationsEntryComponent) : Child()
        class Search(val component: SearchEntryComponent) : Child()
        class CourseDetails(val component: CourseDetailsEntryComponent) : Child()
    }

    class Factory : HomeEntryComponent.Factory, KoinComponent {
        override fun create(componentContext: ComponentContext): HomeEntryComponent {
            return getKoin().get { parametersOf(componentContext) }
        }
    }
}

fun Modifier.paddingBottomWithConsume(paddingValues: PaddingValues): Modifier {
    val bottomPadding = paddingValues.calculateBottomPadding()
    return this then Modifier.padding(bottom = bottomPadding)
        .consumeWindowInsets(PaddingValues(bottom = bottomPadding))
}

@Composable
private fun BottomNavigation(
    onNavigateRecommendations: () -> Unit,
    onNavigateSearch: () -> Unit,
    stack: Value<ChildStack<*, DefaultHomeEntryComponent.Child>>
) {
    val activeChild = stack.subscribeAsState().value.active.instance
    val items = remember(activeChild) {
        listOf(
            NavigationItem(
                Icons.Home,
                onNavigateRecommendations,
                activeChild is DefaultHomeEntryComponent.Child.Recommendations
            ),
            NavigationItem(
                Icons.Search,
                onNavigateSearch,
                activeChild is DefaultHomeEntryComponent.Child.Search
            )
        )
    }
    NavigationBar {
        items.forEach {
            NavigationBarItem(
                selected = it.isActive,
                icon = { Icon(it.icon, contentDescription = null) },
                onClick = it.onClick
            )
        }
    }
}

data class NavigationItem(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val isActive: Boolean
)