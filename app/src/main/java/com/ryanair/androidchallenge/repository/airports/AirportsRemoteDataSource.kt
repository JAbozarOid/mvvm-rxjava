package com.ryanair.androidchallenge.repository.airports

import com.ryanair.androidchallenge.data.airports.network.AirportService
import com.ryanair.androidchallenge.utils.network.NetworkUtil.safeApiCall
import javax.inject.Inject

class AirportsRemoteDataSource @Inject constructor(private val airportService: AirportService) {

    suspend fun getAirports() = safeApiCall {
        airportService.getAirports("")
    }

}