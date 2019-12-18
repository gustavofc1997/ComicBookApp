package com.sundevs.data.network.interceptors

import android.util.Log
import com.sundevs.data.utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest = chain.request()

        Log.d("Endpoint", originalRequest.url.toString())
        Log.d("Endpoint", originalRequest.body?.toString() ?: "")

        val url = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("format", "json")
            .build()
        originalRequest = originalRequest.newBuilder().url(url).build();
        return chain.proceed(originalRequest)

    }
}