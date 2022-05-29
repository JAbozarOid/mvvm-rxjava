package com.ryanair.androidchallenge.repository.routes

import com.ryanair.androidchallenge.data.airports.network.RoutesService
import com.ryanair.androidchallenge.utils.network.NetworkUtil.safeApiCall
import javax.inject.Inject

class RoutesRemoteDataSource @Inject constructor(private val routesService: RoutesService) {

    suspend fun getRoutes() = safeApiCall {
        routesService.getRoutes(departureAirportCode = "STN" , arrivalAirportCode = "DUB")
    }
}