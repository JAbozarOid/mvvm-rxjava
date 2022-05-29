package com.ryanair.androidchallenge.data.airports.network

import com.ryanair.androidchallenge.data.airports.network.model.AirportResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AirportService {

    // ?fields=code,name,seoName,base,timeZone,city.code,city.name,macCity.code,macCity.macCode,macCity.name,region.code,region.name,country.code,country.name,country.currency,coordinates.latitude,coordinates.longitude
    @GET("/locate/v5/airports")
    suspend fun getAirports(@Query("market") countryCode: String): Response<List<AirportResponse>>

    @GET("/locate/v5/airports?fields=code,name,seoName,base,timeZone,city.code,city.name,macCity.code,macCity.macCode,macCity.name,region.code,region.name,country.code,country.name,country.currency,coordinates.latitude,coordinates.longitude")
    fun getAirportsRx(@Query("market") countryCode: String): Single<List<AirportResponse>>

}