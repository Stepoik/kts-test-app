package ru.stepan.reddit.cources

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.cources.list.CourseListComponent
import ru.stepan.reddit.cources.list.DefaultCourseListComponent
import ru.stepan.reddit.courses.domain.usecases.GetFullCoursesUseCase

val coursesUiModule = module {
    single { GetFullCoursesUseCase(get(), get()) }

    factoryOf(::DefaultCourseListComponent) bind CourseListComponent::class
    factory<CourseListComponent.Factory> { DefaultCourseListComponent.Factory() }
}