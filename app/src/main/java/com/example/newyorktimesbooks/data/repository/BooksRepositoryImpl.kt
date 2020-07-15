package com.example.newyorktimesbooks.data.repository

import com.example.newyorktimesbooks.data.dto.BooksResult
import com.example.newyorktimesbooks.data.mapper.mapToDomain
import com.example.newyorktimesbooks.data.response.BookBodyResponse
import com.example.newyorktimesbooks.data.service.ApiService
import com.example.newyorktimesbooks.domain.Book
import com.example.newyorktimesbooks.util.HttpStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepositoryImpl(private val service: ApiService) : BooksRepository {

    override fun getBooks(booksResultCallBack: (result: BooksResult) -> Unit) {
        service.getInstance().getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>, response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books = mutableListOf<Book>()
                        response.body()?.bookResults?.map { result ->
                            result.details?.map { detail ->
                                books.add(detail.mapToDomain())
                            }
                        }
                        booksResultCallBack(BooksResult.Success(books))
                    }
                    response.code() == HttpStatus.UNAUTHORIZED.value -> {
                        booksResultCallBack(BooksResult.ApiError(HttpStatus.UNAUTHORIZED.value))
                    }
                    else -> {
                        booksResultCallBack(BooksResult.ApiError(HttpStatus.BAD_REQUEST.value))
                    }
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallBack(BooksResult.ServerError(false))
            }
        })
    }
}

