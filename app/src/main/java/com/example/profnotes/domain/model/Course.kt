package com.example.profnotes.domain.model

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val tags: Array<String>,
    val status: String,
    val plannedDate: String,
    val text: Array<RichText>
)