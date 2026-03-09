package ru.stepan.reddit.course.details

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.stepan.reddit.course.details.entry.CourseDetailsEntryComponent
import ru.stepan.reddit.course.details.entry.DefaultCourseDetailsEntryComponent
import ru.stepan.reddit.course.details.main.CourseDetailsComponent
import ru.stepan.reddit.course.details.main.DefaultCourseDetailsComponent

val courseDetailsUiModule = module {
    factory<CourseDetailsComponent.Factory> { DefaultCourseDetailsComponent.Factory() }
    factoryOf(::DefaultCourseDetailsComponent) bind CourseDetailsComponent::class

    factory<CourseDetailsEntryComponent.Factory> { DefaultCourseDetailsEntryComponent.Factory() }
    factoryOf(::DefaultCourseDetailsEntryComponent) bind CourseDetailsEntryComponent::class
}