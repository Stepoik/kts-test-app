package ru.stepan.reddit.auth.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Long,
    @SerialName("full_name")
    val fullname: String,
    @SerialName("avatar")
    val avatar: String,
    @SerialName("is_guest")
    val isGuest: Boolean
)
