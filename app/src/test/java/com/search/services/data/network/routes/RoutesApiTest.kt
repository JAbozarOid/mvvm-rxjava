package com.search.services.data.network.routes

import com.ryanair.androidchallenge.repository.routes.RoutesRemoteDataSource
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
class RoutesApiTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var routesRemoteDataSource: RoutesRemoteDataSource


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `getRoutes api response is successful`() {
        runBlocking {
            val response = routesRemoteDataSource.getRoutes()
            genericAssertResponse(response) {
                assertTrue(it.data.isNotEmpty())
            }
        }
    }


}