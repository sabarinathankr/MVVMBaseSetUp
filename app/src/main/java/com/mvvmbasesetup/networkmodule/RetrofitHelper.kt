package com.mvvmbasesetup.networkmodule

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
 
    private const val BASE_URL = "https://reqres.in/api/"
//private const val BASE_URL = "https://quotable.io/"
 
    private fun getInstance(): Retrofit {

        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//        val interceptor = PrettyLoggingInterceptor()
        val clientBuilder: OkHttpClient.Builder =
            client.newBuilder().addInterceptor(interceptor)

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()

    }


    fun getApiInstance(): URLFactory = getInstance().create(URLFactory::class.java)

}