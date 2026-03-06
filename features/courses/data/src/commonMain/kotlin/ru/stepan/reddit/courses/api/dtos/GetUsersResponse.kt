package ru.stepan.reddit.courses.api.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.stepan.reddit.core.data.network.models.PageMeta

@Serializable
data class GetUsersResponse(
    val meta: PageMeta,
    @SerialName("users")
    val users: List<UserDto>
)
