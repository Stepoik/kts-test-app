package ru.stepan.reddit.posts

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.posts.api.PostsRepository
import ru.stepan.reddit.posts.api.PostsRepositoryImpl

val postsDataModule = module {
    singleOf(::PostsRepositoryImpl) bind PostsRepository::class
}