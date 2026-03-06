package ru.stepan.reddit.courses.api.mappers

import ru.stepan.reddit.courses.api.dtos.UserDto
import ru.stepan.reddit.courses.api.models.User

fun UserDto.toDomain(): User {
    return User(
        id = id,
        fullName = fullName
    )
}