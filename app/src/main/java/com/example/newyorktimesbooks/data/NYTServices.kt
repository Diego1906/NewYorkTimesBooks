package com.example.newyorktimesbooks.data

import com.example.newyorktimesbooks.data.model.Book
import retrofit2.http.GET

interface NYTServices {

    @GET("lists.json")
    suspend fun getBooks(): List<Book>
}