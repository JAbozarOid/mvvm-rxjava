package com.ryanair.androidchallenge.data.airports.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RouteResponse(
    @SerializedName("departureAirport")
    val departureAirport: @RawValue Departure?,
    @SerializedName("arrivalAirport")
    val arrivalAirport: @RawValue Arrival?,
    @SerializedName("connectingAirport")
    val connectingAirport: @RawValue Connecting?
) : Parcelable

@Parcelize
data class Arrival(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("macCity")
    val macCity: MacCity?
) : Parcelable

@Parcelize
data class Connecting(
    @SerializedName("code")
     val code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("macCity")
     val macCity: MacCity?
) : Parcelable


@Parcelize
data class Departure(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("macCity")
    val macCity: MacCity?,
) : Parcelable
