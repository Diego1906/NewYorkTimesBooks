package com.example.newyorktimesbooks.data.repository

import com.example.newyorktimesbooks.data.response.BookBodyResponse
import com.example.newyorktimesbooks.data.service.ApiService
import retrofit2.Call

class BooksRepositoryImpl(private val service: ApiService) : BooksRepository {

    override suspend fun getBooks(): BookBodyResponse {
        return service.getInstance().getBooks()
    }

    override fun getBooksWithCall(): Call<BookBodyResponse> {
        return service.getInstance().getBooksWithCall()
    }
}