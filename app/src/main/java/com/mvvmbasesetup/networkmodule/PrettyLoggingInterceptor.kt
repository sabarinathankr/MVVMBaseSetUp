package com.mvvmbasesetup.networkmodule

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

class PrettyLoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val responseBody = response.body

        val rawJson = responseBody.string()
        JsonFormatter.logApiResponse("Code", response.code.toString())
        JsonFormatter.logApiResponse("Response", rawJson)

        val contentType = responseBody.contentType()
        val body = rawJson.toResponseBody(contentType)
        return response.newBuilder().body(body).build()
    }
}