package com.example.newyorktimesbooks.data.repository

import com.example.newyorktimesbooks.data.response.BookBodyResponse
import retrofit2.Call

interface BooksRepository {

    suspend fun getBooks(): BookBodyResponse

    fun getBooksWithCall(): Call<BookBodyResponse>
}