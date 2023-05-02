package com.example.midtest.net

import com.example.midtest.model.Message
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MessageService {
    @GET("api/4/story/{data}/short-comments")
    fun getMessage(@Path("data") data: String): Call<Message>
}