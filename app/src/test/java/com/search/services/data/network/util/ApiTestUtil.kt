package com.search.services.data.network.util

import com.ryanair.androidchallenge.utils.ApiResponse


object ApiTestUtil {

    suspend fun generalAssertResponse(response: ApiResponse<*>) {
        genericAssertResponse(response) {
            assert(true)
        }
    }

    suspend fun <T> genericAssertResponse(
        response: ApiResponse<T>,
        successFun: suspend (response: ApiResponse.Success<T>) -> Unit
    ) {
        when (response) {
            is ApiResponse.Success -> {
                successFun(response)
            }
            else -> {
                assert(false)
            }
        }
    }
}

