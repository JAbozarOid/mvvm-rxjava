package com.ryanair.androidchallenge.data.airports.network

import com.ryanair.androidchallenge.data.airports.network.model.RouteResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RoutesService {

    // fields=departureAirport.code,departureAirport.name,departureAirport.macCity.macCode,arrivalAirport.code,arrivalAirport.name,arrivalAirport.macCity.macCode,connectingAirport.code,connectingAirport.name,connectingAirport.macCity.macCode
    @GET("/locate/v5/routes")
    suspend fun getRoutes(
        @Query(value = "departureAirportCode") departureAirportCode: String? = null,
        @Query(value = "arrivalAirportCode") arrivalAirportCode: String? = null
    ): Response<List<RouteResponse>>

    @GET("/locate/v5/routes?fields=departureAirport.code,departureAirport.name,departureAirport.macCity.macCode,arrivalAirport.code,arrivalAirport.name,arrivalAirport.macCity.macCode,connectingAirport.code,connectingAirport.name,connectingAirport.macCity.macCode")
    fun getRoutesRx(
        @Query(value = "departureAirportCode") departureAirportCode: String? = null,
        @Query(value = "arrivalAirportCode") arrivalAirportCode: String? = null
    ): Single<List<RouteResponse>>
}