package com.example.newyorktimesbooks.util

import android.content.Context
import android.widget.Toast

fun String.onShowToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}