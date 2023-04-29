package com.example.sunnyweather.logic.model
import com.google.gson.annotations.SerializedName


data class RealtimeResponse(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: String // ok
) {
    data class Result(
        @SerializedName("realtime")
        val realtime: Realtime
    ) {
        data class Realtime(
            @SerializedName("air_quality")
            val airQuality: AirQuality,
            @SerializedName("skycon")
            val skycon: String, // WIND
            @SerializedName("temperature")
            val temperature: Double // 23.16
        ) {
            data class AirQuality(
                @SerializedName("aqi")
                val aqi: Aqi
            ) {
                data class Aqi(
                    @SerializedName("chn")
                    val chn: Double // 17.0
                )
            }
        }
    }
}