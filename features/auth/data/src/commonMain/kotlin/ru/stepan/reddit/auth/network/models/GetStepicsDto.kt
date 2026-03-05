package ru.stepan.reddit.auth.network.models

import kotlinx.serialization.Serializable

@Serializable
data class GetStepicsDto(
    val users: List<UserDto>
)
