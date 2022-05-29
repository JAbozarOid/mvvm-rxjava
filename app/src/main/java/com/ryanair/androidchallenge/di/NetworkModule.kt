package com.ryanair.androidchallenge.di

import com.ryanair.androidchallenge.data.airports.network.AirportService
import com.ryanair.androidchallenge.data.airports.network.RoutesService
import com.ryanair.androidchallenge.data.flights.network.FlightService
import com.ryanair.androidchallenge.utils.ApiConstants.Companion.BASE_URL
import com.ryanair.androidchallenge.utils.ApiConstants.Companion.BASE_URL_SERVICES_API
import com.ryanair.androidchallenge.utils.network.NetworkCallAdapterFactory
import com.ryanair.androidchallenge.utils.network.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @CloudRetrofit
    @Provides
    @Singleton
    fun provideOkhttp(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okhttpBuilder =
            OkHttpClient().newBuilder().readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(tokenInterceptor)
        return okhttpBuilder.build()
    }

    @CloudRetrofit
    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @CloudRetrofit
    @Singleton
    @Provides
    fun provideRetrofitInstance(
        @CloudRetrofit okHttpClient: OkHttpClient,
        @CloudRetrofit gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_SERVICES_API)
            .client(okHttpClient)
            .addCallAdapterFactory(NetworkCallAdapterFactory())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }


    @Provides
    @Singleton
    fun provideAirportService(
        @CloudRetrofit
        retrofit: Retrofit
    ): AirportService {
        return retrofit.create(AirportService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoutesService(
        @CloudRetrofit
        retrofit: Retrofit
    ): RoutesService {
        return retrofit.create(RoutesService::class.java)
    }

    @Provides
    @Singleton
    fun provideFlightService(
        @CloudRetrofit
        retrofit: Retrofit
    ): FlightService {
        return retrofit.create(FlightService::class.java)
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CloudRetrofit

