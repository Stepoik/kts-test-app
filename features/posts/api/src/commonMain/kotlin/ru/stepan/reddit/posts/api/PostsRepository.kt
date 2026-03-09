package ru.stepan.reddit.posts.api

import ru.stepan.reddit.posts.api.models.Post

interface PostsRepository {
    fun likePost(id: String, liked: Boolean?): Result<Unit>

    fun getPosts(offset: Int): Result<List<Post>>
}