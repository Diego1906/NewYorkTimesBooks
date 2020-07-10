package com.example.newyorktimesbooks.data.mapper

import com.example.newyorktimesbooks.data.model.Book
import com.example.newyorktimesbooks.data.response.BookDetailsResponse

fun BookDetailsResponse.mapToDomain() = Book(
    title = this.title,
    description = this.description,
    author = this.author
)