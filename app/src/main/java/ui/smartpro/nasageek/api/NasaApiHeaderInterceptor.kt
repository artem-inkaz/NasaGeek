package ui.smartpro.nasageek.api

import okhttp3.Interceptor
import okhttp3.Response
import ui.smartpro.nasageek.BuildConfig

private const val API_KEY_HEADER = "x-api-key"

class NasaApiHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val request = originalRequest.newBuilder()
            .url(originalHttpUrl)
            .addHeader(API_KEY_HEADER, BuildConfig.NASA_API_KEY)
            .build()

        return chain.proceed(request)
    }
}