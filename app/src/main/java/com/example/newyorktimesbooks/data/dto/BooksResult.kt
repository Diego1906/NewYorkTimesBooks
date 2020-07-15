package com.example.newyorktimesbooks.data.dto

import com.example.newyorktimesbooks.domain.Book

sealed class BooksResult {
    class Success(val books: List<Book>) : BooksResult()
    class ApiError(val statusCode: Int, val isTest: Boolean = false) : BooksResult()
    class ServerError(val isTest: Boolean = false) : BooksResult()
}