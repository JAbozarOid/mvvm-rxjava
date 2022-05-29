package com.ryanair.androidchallenge.repository.flights

import javax.inject.Inject

class FlightsRepository @Inject constructor(private val flightsRemoteDataSource: FlightsRemoteDataSource) {

    val remote = flightsRemoteDataSource
}