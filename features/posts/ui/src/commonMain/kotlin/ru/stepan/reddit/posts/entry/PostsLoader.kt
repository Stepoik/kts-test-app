package ru.stepan.reddit.posts.entry

import ru.stepan.reddit.posts.api.models.Post

fun interface PostsLoader {
    suspend fun getPosts(offset: Int): Result<List<Post>>
}