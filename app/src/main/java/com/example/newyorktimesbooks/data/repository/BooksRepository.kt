package com.example.newyorktimesbooks.data.repository

import androidx.lifecycle.LiveData
import com.example.newyorktimesbooks.data.response.BookBodyResponse

interface BooksRepository {

    suspend fun getBooks(): LiveData<BookBodyResponse>
}