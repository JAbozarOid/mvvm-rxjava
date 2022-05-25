package com.ryanair.androidchallenge.di

import com.ryanair.androidchallenge.BuildConfig
import com.ryanair.androidchallenge.airports.network.AirportService
import com.ryanair.androidchallenge.flights.network.FlightService
import com.ryanair.androidchallenge.airports.network.RoutesService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().also {
            if (BuildConfig.DEBUG) {
                it.addInterceptor(loggingInterceptor)
            }
        }.build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        // TODO("add URL provided in the task instructions")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideAirportService(retrofit: Retrofit): AirportService = retrofit.create(AirportService::class.java)

    @Singleton
    @Provides
    fun provideRoutesService(retrofit: Retrofit): RoutesService = retrofit.create(RoutesService::class.java)

    @Singleton
    @Provides
    fun provideFlightService(retrofit: Retrofit): FlightService = retrofit.create(FlightService::class.java)
}