package com.example.newyorktimesbooks.data.repository

import com.example.newyorktimesbooks.data.BooksResult

interface BooksRepository {

    // USING COROUTINES
    // suspend fun getBooks(): BookBodyResponse

    //fun getBooksWithCall(): Call<BookBodyResponse>

    fun getBooks(booksResultCallBack: (result: BooksResult) -> Unit)
}