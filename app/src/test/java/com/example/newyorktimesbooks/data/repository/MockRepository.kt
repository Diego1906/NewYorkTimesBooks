package com.example.newyorktimesbooks.data.repository

import com.example.newyorktimesbooks.data.dto.BooksResult

class MockRepository(private val result: BooksResult) : BooksRepository {

    override fun getBooks(booksResultCallBack: (result: BooksResult) -> Unit) {
        booksResultCallBack(result)
    }
}