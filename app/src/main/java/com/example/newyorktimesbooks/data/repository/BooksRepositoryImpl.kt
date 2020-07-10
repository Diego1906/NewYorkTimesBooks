package com.example.newyorktimesbooks.data.repository

import androidx.lifecycle.LiveData
import com.example.newyorktimesbooks.data.response.BookBodyResponse
import com.example.newyorktimesbooks.data.service.ApiService

class BooksRepositoryImpl(private val service: ApiService) : BooksRepository {

    override suspend fun getBooks(): LiveData<BookBodyResponse> {
        return service.getService().getBooks()
    }
}