package ru.stepan.reddit.core.common.result

import kotlinx.coroutines.CancellationException

inline fun <R> coroutinesRunCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (t: Throwable) {
        Result.failure(t)
    }
}