package com.ryanair.androidchallenge.utils.network

import com.ryanair.androidchallenge.utils.ApiConstants.Companion.ERROR_NO_INTERNET_CONNECTION
import com.ryanair.androidchallenge.utils.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

object NetworkUtil {

    suspend inline fun <T> safeApiCall(
        crossinline body: suspend () -> Response<T>
    ): ApiResponse<T> {
        return try {
            // blocking block
            val response = withContext(Dispatchers.IO) {
                body()
            }
            ApiResponse.create(response)
        } catch (e: Exception) {
            ApiResponse.create(e)
        }
    }

    inline fun <T> genericRequestCollect(
        crossinline body: suspend () -> ApiResponse<T>,
        networkListener: NetworkListener,
        coroutineScope: CoroutineScope,
        crossinline collectFunction: (ApiResponse<T>) -> Unit
    ) = coroutineScope.launch(Dispatchers.IO) {
        flowResponse(
            body = body,
            networkListener = networkListener
        ).collect {
            collectFunction(it)
        }
    }

    inline fun <T> flowResponse(
        crossinline body: suspend () -> ApiResponse<T>,
        networkListener: NetworkListener
    ) = flow {
        if (networkListener.isConnected) {
            emit(ApiResponse.Loading())
            val result = body()
            emit(result)
        } else {
            emit(ApiResponse.ErrorTryAgain(ERROR_NO_INTERNET_CONNECTION))
        }
    }.flowOn(Dispatchers.IO)
}