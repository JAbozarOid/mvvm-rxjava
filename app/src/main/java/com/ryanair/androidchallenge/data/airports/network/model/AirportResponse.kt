package com.ryanair.androidchallenge.data.airports.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class AirportResponse(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("seoName")
    val seoName: String?,
    @SerializedName("base")
    val isBase: Boolean?,
    @SerializedName("timeZone")
    val timeZone: String?,
    @SerializedName("city")
    val city: @RawValue City?,
    @SerializedName("macCity")
    val macCity: @RawValue MacCity?,
    @SerializedName("region")
    val region: @RawValue Region?,
    @SerializedName("country")
    val country: @RawValue Country?,
    @SerializedName("coordinates")
    val coordinates: @RawValue Coordinates?
) : Parcelable

@Parcelize
data class City(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?
) : Parcelable

@Parcelize
data class MacCity(
    @SerializedName("code")
    val code: String?,
    @SerializedName("macCode")
    val macCode: String?,
    @SerializedName("name")
    val name: String?
) : Parcelable

@Parcelize
data class Region(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?
) : Parcelable

@Parcelize
data class Country(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("currency")
    val currencyCode: String?
) : Parcelable

@Parcelize
data class Coordinates(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
) : Parcelable
