package com.example.newyorktimesbooks.data

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiService {

    val URL_BASE = "https://api.nytimes.com/svc/books/v3/"

    private val retrofit: Retrofit

    init {
        val moshi = Moshi.Builder().build()

        retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun getInstace(): NYTServices {
        return retrofit.create(NYTServices::class.java)
    }
}