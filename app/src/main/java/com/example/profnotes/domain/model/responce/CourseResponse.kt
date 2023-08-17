package com.example.profnotes.domain.model.responce

import com.example.profnotes.domain.model.entity.Course
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CourseResponse(
    val status: String,
    val message: String?,
    val data: Array<Course>
)
