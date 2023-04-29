package com.example.sunnyweather.logic.network

import PlaceResponse
import retrofit2.Call
import com.example.sunnyweather.SunnyWeatherApplication
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}