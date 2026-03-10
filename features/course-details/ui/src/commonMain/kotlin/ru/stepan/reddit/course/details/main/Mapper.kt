package ru.stepan.reddit.course.details.main

import ru.stepan.reddit.courses.api.models.Course

fun Course.toFullCourseVO(): FullCourseVO {
    return FullCourseVO(
        title = title,
        summary = summary,
        description = description,
        acquiredSkills = acquiredSkills
    )
}