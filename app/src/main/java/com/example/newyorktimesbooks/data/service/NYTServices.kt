package com.example.newyorktimesbooks.data.service

import com.example.newyorktimesbooks.BuildConfig
import com.example.newyorktimesbooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = BuildConfig.API_KEY,
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}