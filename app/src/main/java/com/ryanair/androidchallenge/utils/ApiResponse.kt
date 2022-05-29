package com.ryanair.androidchallenge.utils

import android.telephony.euicc.EuiccManager.ERROR_TIME_OUT
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.ryanair.androidchallenge.utils.ApiConstants.Companion.ERROR_NO_INTERNET_CONNECTION
import com.ryanair.androidchallenge.utils.ApiConstants.Companion.ERROR_NO_SERVER
import com.ryanair.androidchallenge.utils.ApiConstants.Companion.ERROR_RESPONSE_TIME_OUT
import com.ryanair.androidchallenge.utils.ApiConstants.Companion.ERROR_UNKNOWN_ERROR
import retrofit2.Response


sealed class ApiResponse<T> {


    companion object {
        fun <T> create(error: Throwable): ErrorTryAgain<T> {
            var errorMessage = error.message
            errorMessage = when {
                errorMessage?.contains("timeout") == true -> {
                    ERROR_RESPONSE_TIME_OUT
                }
                errorMessage?.contains(ERROR_NO_SERVER, true) == true -> {
                    ERROR_NO_INTERNET_CONNECTION
                }
                else -> {
                    ERROR_UNKNOWN_ERROR
                }
            }
            return ErrorTryAgain(
                errorMessage = errorMessage
            )
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            if (response.isSuccessful) {
                val body = response.body()
                return Success(data = body!!)
            } else {
                val gson = Gson()
                val type = object : TypeToken<JsonObject>() {}.type
                val errorResponse: JsonObject? =
                    gson.fromJson(response.errorBody()!!.charStream(), type)
                val msg = errorResponse?.asJsonObject?.get("message")?.toString() ?: ""
                val code = errorResponse?.asJsonObject?.get("code").toString()

                return Error(
                    errorMessage = msg,
                    httpCode = response.code().toString(),
                    code = code,
                    url = response.raw().request.url.encodedPath
                )
            }
        }
    }

    class Loading<T> : ApiResponse<T>()
    class ErrorTryAgain<T>(
        val errorMessage: String,
    ) : ApiResponse<T>()

    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error<T>(
        val errorMessage: String,
        val httpCode: String = "",
        val code: String = "",
        val url: String = ""
    ) : ApiResponse<T>()
}
