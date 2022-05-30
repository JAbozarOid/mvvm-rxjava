package com.ryanair.androidchallenge.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ryanair.androidchallenge.data.airports.network.model.AirportResponse
import com.ryanair.androidchallenge.repository.airports.AirportsRepository
import com.ryanair.androidchallenge.repository.routes.RoutesRepository
import com.ryanair.androidchallenge.utils.ApiResponse
import com.ryanair.androidchallenge.utils.network.NetworkListener
import com.ryanair.androidchallenge.utils.network.NetworkUtil.genericRequestCollect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val airportsRepository: AirportsRepository,
    private val routesRepository: RoutesRepository,
    private val mNetworkListener: NetworkListener,
    application: Application
) : AndroidViewModel(application) {

    private val _isProgressEnable: MutableLiveData<Boolean> = MutableLiveData()
    val isProgressEnable: LiveData<Boolean> get() = _isProgressEnable

    private val _airportRes: MutableLiveData<ApiResponse<List<AirportResponse>>> = MutableLiveData()
    val airportRes: LiveData<ApiResponse<List<AirportResponse>>> get() = _airportRes

    fun setProgressBarVisibility(isVisible: Boolean) {
        _isProgressEnable.postValue(isVisible)
    }

    fun showToastErrorMsg(errorMsg: String) {
        Toast.makeText(getApplication(), errorMsg, Toast.LENGTH_SHORT).show()
    }


    fun getAirports() {
        genericRequestCollect(
            body = {
                airportsRepository.remote.getAirports()
            },
            mNetworkListener,
            viewModelScope
        ) {
            _airportRes.postValue(it)
        }

    }
}