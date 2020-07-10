package com.example.newyorktimesbooks.presentation.books

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newyorktimesbooks.R
import kotlinx.android.synthetic.main.toolbar.*

class BooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        toolbarMain.title = getString(R.string.title)
        setSupportActionBar(toolbarMain)
    }
}
