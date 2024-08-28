package com.mvvmbasesetup.networkmodule

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface URLFactory {
    //    @GET("unknown/23")
    @GET("users?page=2")
    fun test(): Call<ResponseBody>
}