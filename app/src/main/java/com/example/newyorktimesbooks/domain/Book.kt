package com.example.newyorktimesbooks.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val title: String = "",
    val description: String = "",
    val author: String = ""
) : Parcelable