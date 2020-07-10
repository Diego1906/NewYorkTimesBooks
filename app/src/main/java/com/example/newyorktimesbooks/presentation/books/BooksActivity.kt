package com.example.newyorktimesbooks.presentation.books

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.data.model.Book
import com.example.newyorktimesbooks.presentation.books.adapter.BooksAdapter
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.toolbar.*

class BooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        toolbarMain.title = getString(R.string.books_title)
        setSupportActionBar(toolbarMain)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(recyclerBooks) {
            setHasFixedSize(true)
            adapter = BooksAdapter(getBooks())
        }
    }

    private fun getBooks() = listOf(
        Book(title = "Kotlin em ação", author = "Demitriv"),
        Book(title = "Kotlin", author = "Desconhecido"),
        Book(title = "Android com Kotlin", author = "Ricado Lecheta")
    )
}
