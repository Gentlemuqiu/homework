package com.example.midtest.net


import com.example.midtest.model.latest
import retrofit2.Call
import retrofit2.http.GET
interface LatestService {
    @GET("api/4/news/latest")
    fun latestNew(): Call<latest>
}