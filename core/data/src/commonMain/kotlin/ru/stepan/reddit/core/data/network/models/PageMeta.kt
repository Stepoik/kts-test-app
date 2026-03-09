package ru.stepan.reddit.core.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageMeta(
    @SerialName("page")
    val page: Int,
    @SerialName("has_next")
    val hasNext: Boolean,
    @SerialName("has_previous")
    val hasPrevious: Boolean
)

val PageMeta.nextPage: Int? get() = if (hasNext) page + 1 else null