package com.example.newyorktimesbooks.util

import android.content.Context
import android.widget.Toast
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.application.App

fun String.onShowToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String?.checkDataApi(): String {
    return this?.let {
        this
    } ?: App.getContext().getString(R.string.unreported_information)
}