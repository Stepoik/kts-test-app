package ru.stepan.reddit.posts.entry

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import ru.stepan.reddit.core.ui.decompose.BaseScreenComponent
import ru.stepan.reddit.posts.api.PostsRepository
import ru.stepan.reddit.posts.composables.PostItemVO

internal class DefaultPostsComponent(
    componentContext: ComponentContext,
    private val postsLoader: PostsLoader,
    private val postsRepository: PostsRepository
) : PostsComponent, BaseScreenComponent<PostsState, Unit>(
    componentContext = componentContext,
    PostsState(),
    PostsState.serializer()
) {
    override fun onLikePost(postId: String, liked: Boolean) {
        val state = state.value
        componentScope.launch(Dispatchers.Default) {
            val postIndex = state.posts.indexOfFirst { it.id == postId }
            if (postIndex == -1) return@launch

            val post = state.posts.getOrNull(postIndex) ?: return@launch
            val newPost = post.applyLike(liked)
            val newPosts = state.posts.toMutableList()
            newPosts[postIndex] = newPost

            updateState { it.copy(posts = newPosts) }
            postsRepository.likePost(postId, liked = newPost.liked)
        }
    }

    override fun onLoadNext() {
        val state = state.value
        if (state.isLoading) return

        updateState { it.copy(isLoading = true) }
        componentScope.launch(Dispatchers.Default) {
            postsLoader.getPosts(state.posts.size)
                .onSuccess { newPosts ->
                    updateState {
                        it.copy(
                            posts = it.posts + newPosts.map { it.toVO() },
                            isLoading = false
                        )
                    }
                }
                .onFailure {
                    updateState {
                        it.copy(isLoading = false)
                    }
                }
        }
    }

    override fun onSaveState(state: PostsState): PostsState {
        return state.copy(isLoading = false)
    }

    private fun PostItemVO.applyLike(liked: Boolean): PostItemVO {
        val (newLiked, score) = when {
            liked && this.liked == null -> liked to (score + 1)
            !liked && this.liked == null -> liked to (score - 1)
            liked && this.liked == true -> null to (score - 1)
            liked && this.liked == false -> liked to (score + 2)
            !liked && this.liked == false -> null to (score + 1)
            !liked && this.liked == true -> liked to (score - 2)
            else -> this.liked to score
        }
        return copy(liked = newLiked, score = score)
    }

    class Factory : PostsComponent.Factory, KoinComponent {
        override fun create(
            componentContext: ComponentContext,
            postsLoader: PostsLoader
        ): PostsComponent {
            return getKoin().get { parametersOf(componentContext, postsLoader) }
        }
    }
}