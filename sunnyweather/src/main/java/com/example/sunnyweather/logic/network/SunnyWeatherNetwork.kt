package com.example.sunnyweather.logic.network

import android.util.Log
import com.example.sunnyweather.logic.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {
    private val placeService = ServiceCreator.create<PlaceService>()
    private val weatherService=ServiceCreator.create<WeatherService>()

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()
    suspend fun getDailyWeather(lng :String,lat: String)= weatherService.getDailyWeather(lng,lat).await()
    suspend fun getRealtimeWeather(lng: String,lat: String)= weatherService.getRealtimeWeather(lng,lat).await()

    //使用协程简化回调
    private suspend fun <T> Call<T>.await(): T {
        //suspendCoroutine函数必须在协程作用域或挂起函数中才能调用,主要作用是将当前协程立即挂起,然后在一个普通线程中执行Lambda表达式中代码.
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    Log.d("hui", "onResponse:${body} ")
                    //让协程恢复
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}