package com.mvvmbasesetup.networkmodule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

open class APICallBacks {

    fun rawResponse(test: Call<ResponseBody>)  : LiveData<Result<String>> {
        val liveData = MutableLiveData<Result<String>>()
        val request: Request = test.request()
        Log.d("API Request", "URL: ${request.url}")
        Log.d("API Request", "Method: ${request.method}")
        Log.d("API Request", "Headers: ${request.headers}")
        Log.d("API Request", "Body: ${requestBodyToString(request.body)}")
        test.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.body() != null) {
                    try {
                        val responseString = response.body()!!.string()
                        liveData.postValue(Result.success(responseString))
                    } catch (e: IOException) {
                        e.printStackTrace()
                        liveData.postValue(Result.failure(e))
                    }
                } else {
                    liveData.postValue(Result.failure(Exception("Request failed: ${response.code()}")))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                liveData.postValue(Result.failure(t))
            }
        })
        return liveData
    }
    //to convert okHttps string to request
    private fun requestBodyToString(requestBody: RequestBody?): String {
        return try {
            val buffer = Buffer()
            requestBody?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            "Error reading request body: ${e.message}"
        }
    }
}