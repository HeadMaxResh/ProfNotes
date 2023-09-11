package com.example.profnotes.domain.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EnterRequest(
    val phone: String,
    val passwordHashed: String
)