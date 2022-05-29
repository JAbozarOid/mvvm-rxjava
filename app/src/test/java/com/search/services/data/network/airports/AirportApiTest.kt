package com.search.services.data.network.airports

import com.ryanair.androidchallenge.repository.airports.AirportsRemoteDataSource
import com.search.services.data.network.util.ApiTestUtil.genericAssertResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class AirportApiTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var airportsRemoteDataSource: AirportsRemoteDataSource


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `getAirport api response is successful`() {
        runBlocking {
            val response = airportsRemoteDataSource.getAirports()
            genericAssertResponse(response) {
                assertTrue(it.data.isNotEmpty())
            }
        }
    }


}