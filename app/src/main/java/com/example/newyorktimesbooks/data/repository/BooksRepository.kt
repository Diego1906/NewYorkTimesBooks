package com.example.newyorktimesbooks.data.repository

import com.example.newyorktimesbooks.data.response.BookBodyResponse

interface BooksRepository {

    suspend fun getBooks(): BookBodyResponse
}