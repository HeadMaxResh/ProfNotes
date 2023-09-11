package com.example.profnotes.data.api

import com.example.profnotes.domain.model.CommunityNote
import com.example.profnotes.domain.model.Course
import com.example.profnotes.domain.request.EnterRequest
import com.example.profnotes.domain.request.RegistrationRequest
import com.example.profnotes.domain.responce.AuthenticationResponse
import com.example.profnotes.domain.responce.CourseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("courses")
    suspend fun getCourses(): CourseResponse

    @GET("courses/{id}")
    suspend fun getCourseById(@Path("id") id: Int): Course

    @GET("community_notes")
    suspend fun getCommunityNotes(): List<CommunityNote>

    @GET("community_notes/{id}")
    suspend fun getCommunityNoteById(@Path("id") id: Int): CommunityNote

    @POST("auth")
    fun auth(@Body request: EnterRequest): Call<AuthenticationResponse>

    @POST("register")
    fun register(@Body request: RegistrationRequest): Call<AuthenticationResponse>
}