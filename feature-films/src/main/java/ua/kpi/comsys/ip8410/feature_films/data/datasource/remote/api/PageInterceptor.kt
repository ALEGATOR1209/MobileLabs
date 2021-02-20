package ua.kpi.comsys.ip8410.feature_films.data.datasource.remote.api

import okhttp3.Interceptor
import okhttp3.Response

class PageInterceptor(
    private val page: Int
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val url = originalRequest.url().newBuilder()
            .addQueryParameter("page", page.toString())
            .build()

        val request = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}