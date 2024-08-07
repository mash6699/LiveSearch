package com.mash.livesearch.domain

import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            val errorBody = response.body.string()
            throw ApiException(response.code, errorBody)
        }
        return response
    }
}