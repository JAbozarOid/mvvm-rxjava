package com.ryanair.androidchallenge.viewModel

import androidx.lifecycle.ViewModel
import com.ryanair.androidchallenge.repository.airports.AirportsRepository
import com.ryanair.androidchallenge.repository.routes.RoutesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val airportsRepository: AirportsRepository,
    private val routesRepository: RoutesRepository
) : ViewModel() {



}