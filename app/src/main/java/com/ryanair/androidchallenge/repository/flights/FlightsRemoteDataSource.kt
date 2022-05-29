package com.ryanair.androidchallenge.repository.flights

import com.ryanair.androidchallenge.data.flights.network.FlightService
import com.ryanair.androidchallenge.utils.network.NetworkUtil.safeApiCall
import javax.inject.Inject

class FlightsRemoteDataSource @Inject constructor(private val flightService: FlightService) {


    suspend fun getFlights() = safeApiCall {
        flightService.getFlights(date = "", origin = "", destination = "", adult = 1, teen = 0 , child = 0, inf = 0)
    }
}