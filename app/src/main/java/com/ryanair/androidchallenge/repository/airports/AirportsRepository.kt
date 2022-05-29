package com.ryanair.androidchallenge.repository.airports

import javax.inject.Inject

class AirportsRepository @Inject constructor(
    private val airportsRemoteDataSource: AirportsRemoteDataSource
) {

    val remote = airportsRemoteDataSource
}