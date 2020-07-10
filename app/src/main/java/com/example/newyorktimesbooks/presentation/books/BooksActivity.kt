package com.example.newyorktimesbooks.presentation.books

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.data.model.Book
import com.example.newyorktimesbooks.presentation.books.adapter.BooksAdapter
import com.example.newyorktimesbooks.presentation.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.toolbar.*

class BooksActivity : AppCompatActivity() {

    private val viewModel: BooksViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(BooksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        toolbarMain.title = getString(R.string.books_title)
        setSupportActionBar(toolbarMain)

        viewModel.books.observe(this, Observer {
            it?.let {
                initRecyclerView(it)
            }
        })

        viewModel.getBooks()
    }

    private fun initRecyclerView(books: List<Book>) {
        with(recyclerBooks) {
            setHasFixedSize(true)
            adapter = BooksAdapter(books)
        }
    }
}
