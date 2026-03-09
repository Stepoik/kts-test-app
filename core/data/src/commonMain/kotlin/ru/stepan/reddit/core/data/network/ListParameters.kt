package ru.stepan.reddit.core.data.network

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter

// Всегда указывать как name[]
fun HttpRequestBuilder.listParameters(name: String, values: List<Any>) {
    require(name.endsWith("[]"))

    values.forEach { parameter(name, it) }
}