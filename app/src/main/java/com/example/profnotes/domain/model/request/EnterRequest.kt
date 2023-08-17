package com.example.profnotes.domain.model.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EnterRequest(
    val phone: String,
    val passwordHashed: String
)