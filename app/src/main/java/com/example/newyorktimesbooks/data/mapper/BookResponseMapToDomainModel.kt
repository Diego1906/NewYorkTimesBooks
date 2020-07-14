package com.example.newyorktimesbooks.data.mapper

import com.example.newyorktimesbooks.data.response.BookDetailsResponse
import com.example.newyorktimesbooks.domain.Book
import com.example.newyorktimesbooks.util.checkDataApi

fun BookDetailsResponse.mapToDomain() =
    Book(
        title = this.title.checkDataApi(),
        description = this.description.checkDataApi(),
        author = this.author.checkDataApi()
    )