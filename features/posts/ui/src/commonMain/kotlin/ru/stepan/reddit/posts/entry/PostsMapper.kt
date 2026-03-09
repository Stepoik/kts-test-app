package ru.stepan.reddit.posts.entry

import ru.stepan.reddit.posts.api.models.Post
import ru.stepan.reddit.posts.composables.PostItemVO

fun Post.toVO(): PostItemVO {
    return PostItemVO(
        id = id,
        name = name,
        title = title,
        selftext = selftext,
        time = time,
        commentsCount = commentsCount,
        author = author,
        ups = ups,
        downs = downs,
        score = score,
        liked = liked
    )
}