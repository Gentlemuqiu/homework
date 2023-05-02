package com.example.midtest.net

import com.example.midtest.model.before
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BeforeService {
    @GET("api/4/news/before/{data}")
    fun getBefore(@Path("data") data: String): Call<before>
}