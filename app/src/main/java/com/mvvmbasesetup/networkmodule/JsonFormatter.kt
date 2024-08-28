package com.mvvmbasesetup.networkmodule

import android.annotation.SuppressLint
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object JsonFormatter {
    private const val TAG = "API_RESPONSE"

    private fun formatApiResponse(apiResponse: String?): String {
        return try {
            if (apiResponse?.startsWith("{") == true) {
                val jsonObject = JSONObject(apiResponse)
                jsonObject.toString(4)
            } else if (apiResponse?.startsWith("[") == true) {
                val jsonArray = JSONArray(apiResponse)
                jsonArray.toString(4)
            } else {
                apiResponse ?: "null"
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            apiResponse ?: "null"
        }
    }

    @SuppressLint("LogNotTimber")
    fun logApiResponse(value: String,apiResponse: String?) {
        Log.d(TAG, "$value: ${formatApiResponse(apiResponse)}")
    }
}
