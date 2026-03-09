package ru.stepan.reddit.courses.api.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Long,
    @SerialName("full_name")
    val fullName: String
)
