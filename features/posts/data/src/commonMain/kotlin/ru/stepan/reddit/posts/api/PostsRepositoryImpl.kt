package ru.stepan.reddit.posts.api

import ru.stepan.reddit.posts.api.models.Post
import kotlin.time.Clock

class PostsRepositoryImpl : PostsRepository {
    override fun likePost(id: String, liked: Boolean?): Result<Unit> {
        return Result.success(Unit)
    }

    override fun getPosts(offset: Int): Result<List<Post>> {
        return Result.success(mockPosts(offset))
    }
    fun mockPosts(offset: Int): List<Post> {
        val now = Clock.System.now().epochSeconds

        return List(25) { index ->
            val globalIndex = offset + index

            val ups = (globalIndex * 37) % 5000
            val downs = (globalIndex * 13) % 400
            val score = ups - downs

            Post(
                id = "id_$globalIndex",
                name = "t3_id_$globalIndex",
                title = "Mock post title #$globalIndex",
                selftext = "This is mock selftext for post #$globalIndex. Generated for testing pagination.",
                time = "${(now - globalIndex * 60_000L)}", // минута назад за каждый offset
                commentsCount = (globalIndex * 11) % 1000,
                author = "user_$globalIndex",
                ups = ups,
                downs = downs,
                score = score,
                liked = when (globalIndex % 3) {
                    0 -> true
                    1 -> false
                    else -> null
                }
            )
        }
    }

}