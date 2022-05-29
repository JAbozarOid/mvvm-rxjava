package com.ryanair.androidchallenge.utils

class ApiConstants {

    companion object {

        // base url for AirportService and RoutesService
        const val BASE_URL_SERVICES_API = "https://services-api.ryanair.com/"

        // base url for flight services
        const val BASE_URL = "https://www.ryanair.com/api/"

        const val ERROR_RESPONSE_TIME_OUT = "Response Timeout"
        const val ERROR_NO_INTERNET_CONNECTION = "No Internet Connection."
        const val ERROR_NO_SERVER = "unable to resolve host"
        const val ERROR_UNKNOWN_ERROR = "Unknown Error"
    }
}