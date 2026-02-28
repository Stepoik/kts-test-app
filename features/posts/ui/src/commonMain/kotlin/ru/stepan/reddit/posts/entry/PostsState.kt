package ru.stepan.reddit.posts.entry

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import ru.stepan.reddit.posts.composables.PostItemVO

@Serializable
data class PostsState(
    val isLoading: Boolean = false,
    @Transient
    val posts: List<PostItemVO> = listOf()
)
