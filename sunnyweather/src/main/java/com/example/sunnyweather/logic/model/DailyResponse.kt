package com.example.sunnyweather.logic.model
import com.google.gson.annotations.SerializedName


data class DailyResponse(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: String // ok
) {
    data class Result(
        @SerializedName("daily")
        val daily: Daily
    ) {
        data class Daily(
            @SerializedName("life_index")
            val lifeIndex: LifeIndex,
            @SerializedName("skycon")
            val skycon: List<Skycon>,
            @SerializedName("temperature")
            val temperature: List<Temperature>
        ) {
            data class LifeIndex(
                @SerializedName("carWashing")
                val carWashing: List<CarWashing>,
                @SerializedName("coldRisk")
                val coldRisk: List<ColdRisk>,
                @SerializedName("dressing")
                val dressing: List<Dressing>,
                @SerializedName("ultraviolet")
                val ultraviolet: List<Ultraviolet>
            ) {
                data class CarWashing(
                    @SerializedName("desc")
                    val desc: String // 适宜
                )

                data class ColdRisk(
                    @SerializedName("desc")
                    val desc: String // 易发
                )

                data class Dressing(
                    @SerializedName("desc")
                    val desc: String // 舒适
                )

                data class Ultraviolet(
                    @SerializedName("desc")
                    val desc: String // 无
                )
            }

            data class Skycon(
                @SerializedName("date")
                val date: String, // 2019-10-20T00:00+08:00
                @SerializedName("value")
                val value: String // CLOUDY
            )

            data class Temperature(
                @SerializedName("max")
                val max: Double, // 25.7
                @SerializedName("min")
                val min: Double // 20.3
            )
        }
    }
}