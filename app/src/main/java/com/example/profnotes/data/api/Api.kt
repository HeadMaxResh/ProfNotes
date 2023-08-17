package com.example.profnotes.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://ktortogether.profsoft.online/")
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()).withNullSerialization()
        )
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}