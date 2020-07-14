package com.example.newyorktimesbooks.data

import com.example.newyorktimesbooks.domain.Book

sealed class BooksResult {
    class Success(val books: List<Book>) : BooksResult()
    class ApiError(val statusCode: Int) : BooksResult()
    object ServerError : BooksResult()
    // object ServerError(val throwable: Throwable) : BooksResult()
}