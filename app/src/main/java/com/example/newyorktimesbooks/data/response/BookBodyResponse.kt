package com.example.newyorktimesbooks.data.response

import com.squareup.moshi.Json

data class BookBodyResponse(
    @Json(name = "results") val bookResults: List<BookResultsResponse>
)