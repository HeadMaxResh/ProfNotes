package com.example.profnotes.data.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor() : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${Token.token}")
            .build()

        return chain.proceed(request)
    }
}