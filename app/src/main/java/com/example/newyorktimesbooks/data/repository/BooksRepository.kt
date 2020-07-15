package com.example.newyorktimesbooks.data.repository

import com.example.newyorktimesbooks.data.dto.BooksResult

interface BooksRepository {

    fun getBooks(booksResultCallBack: (result: BooksResult) -> Unit)
}