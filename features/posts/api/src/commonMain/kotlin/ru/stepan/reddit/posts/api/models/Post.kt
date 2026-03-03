package ru.stepan.reddit.posts.api.models

data class Post(
    val id: String,
    val name: String,
    val title: String,
    val selftext: String,
    val time: String,
    val commentsCount: Int,
    val author: String,
    val ups: Int,
    val downs: Int,
    val score: Int,
    val liked: Boolean?
)
