package com.example.profnotes.domain.model.responce

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TokenResponse(
    val token: String
)