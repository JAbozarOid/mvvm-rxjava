package com.ryanair.androidchallenge.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ryanair.androidchallenge.data.airports.network.model.AirportResponse
import com.ryanair.androidchallenge.repository.airports.AirportsRepository
import com.ryanair.androidchallenge.repository.routes.RoutesRepository
import com.ryanair.androidchallenge.utils.ApiResponse
import com.ryanair.androidchallenge.utils.network.NetworkListener
import com.ryanair.androidchallenge.utils.network.NetworkUtil.genericRequestCollect
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val airportsRepository: AirportsRepository,
    private val routesRepository: RoutesRepository,
    private val mNetworkListener: NetworkListener,
    application: Application
) : AndroidViewModel(application) {

    private val _isProgressEnable: PublishSubject<Boolean> = PublishSubject.create()
    val isProgressEnable: PublishSubject<Boolean> get() = _isProgressEnable

    private lateinit var _airportsRes: Single<ApiResponse<List<AirportResponse>>>

    private var postSubject: BehaviorSubject<List<AirportResponse>> =
        BehaviorSubject.create()

    private fun setProgressBarVisibility(isVisible: Boolean) {
        _isProgressEnable.onNext(isVisible)
    }

    private fun showToastErrorMsg(errorMsg: String) {
        Toast.makeText(getApplication(), errorMsg, Toast.LENGTH_SHORT).show()
    }


    fun getAirports(): BehaviorSubject<List<AirportResponse>> {
        genericRequestCollect(
            body = {
                airportsRepository.remote.getAirports()
            },
            mNetworkListener,
            viewModelScope
        ) {
            when(it) {
                is ApiResponse.Error -> TODO()
                is ApiResponse.ErrorTryAgain -> TODO()
                is ApiResponse.Loading -> TODO()
                is ApiResponse.Success -> {
                    postSubject.onNext(it.data)
                }
            }
        }

        return postSubject
    }
}