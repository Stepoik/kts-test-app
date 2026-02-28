package ru.stepan.reddit.feed.feed

import kotlinx.serialization.Serializable

@Serializable
data class FeedState(
    val isLoading: Boolean
)