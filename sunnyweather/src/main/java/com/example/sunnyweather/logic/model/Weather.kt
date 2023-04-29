package com.example.sunnyweather.logic.model

data class Weather(val realtime: RealtimeResponse.Result.Realtime,val daily: DailyResponse.Result.Daily)
