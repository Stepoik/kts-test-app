package ru.stepan.reddit.core.data.network

import io.ktor.client.HttpClient

fun defaultKtorClient(): HttpClient {
    return HttpClient(HttpEngineFactory().createEngine()) {
        commonConfig()
    }
}