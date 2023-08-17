package com.example.profnotes.data.api

import com.example.profnotes.domain.model.entity.Course
import com.example.profnotes.domain.model.request.EnterRequest
import com.example.profnotes.domain.model.request.RegistrationRequest
import com.example.profnotes.domain.model.responce.AuthenticationResponse
import com.example.profnotes.domain.model.responce.CourseResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("courses")
    suspend fun getCourses(): CourseResponse

    @GET("courses/{id}")
    suspend fun getCourseById(@Path("id") id: Int): Course

    @POST("auth")
    fun auth(@Body request: EnterRequest): Call<AuthenticationResponse>

    @POST("register")
    fun register(@Body request: RegistrationRequest): Call<AuthenticationResponse>
}