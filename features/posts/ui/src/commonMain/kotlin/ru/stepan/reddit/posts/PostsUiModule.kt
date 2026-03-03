package ru.stepan.reddit.posts

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.posts.entry.DefaultPostsComponent
import ru.stepan.reddit.posts.entry.PostsComponent

val postsUiModule = module {
    factoryOf(::DefaultPostsComponent) bind PostsComponent::class
    factory<PostsComponent.Factory> { DefaultPostsComponent.Factory() }
}