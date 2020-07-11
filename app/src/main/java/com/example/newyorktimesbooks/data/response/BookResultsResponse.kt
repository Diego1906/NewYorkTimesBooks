package com.example.newyorktimesbooks.data.response

import com.squareup.moshi.Json

data class BookResultsResponse(
    @Json(name = "book_details")
    val details: List<BookDetailsResponse>? = null
)