package com.example.newyorktimesbooks.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val title: String = "",
    val description: String = "",
    val author: String = ""
) : Parcelable