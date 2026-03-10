package ru.stepan.reddit.courses.api

import org.koin.dsl.module
import ru.stepan.reddit.core.data.CoreQualifiers

val coursesDataModule = module {
    single<CoursesRepository> { CoursesRepositoryImpl(get(CoreQualifiers.AUTHORIZED_KTOR_CLIENT)) }
    single<ReviewRepository> { ReviewRepositoryImpl(get(CoreQualifiers.AUTHORIZED_KTOR_CLIENT)) }
    single<UserRepository> { UserRepositoryImpl(get(CoreQualifiers.AUTHORIZED_KTOR_CLIENT)) }
}