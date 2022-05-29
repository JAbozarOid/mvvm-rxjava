package com.ryanair.androidchallenge.repository.routes

import javax.inject.Inject

class RoutesRepository @Inject constructor(private val routesRemoteDataSource: RoutesRemoteDataSource) {


    val remote = routesRemoteDataSource
}