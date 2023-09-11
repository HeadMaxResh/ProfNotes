package com.example.profnotes.domain.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationRequest(
    val name: String,
    val surname: String,
    val avatar: String?,
    val phone: String,
    val passwordHashed: String,

)