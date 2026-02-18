package ru.stepan.example

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform