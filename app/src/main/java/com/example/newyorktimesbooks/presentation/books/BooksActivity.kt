package com.example.newyorktimesbooks.presentation.books

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.presentation.base.BaseActivity
import com.example.newyorktimesbooks.presentation.books.adapter.BooksAdapter
import com.example.newyorktimesbooks.presentation.books.adapter.OnclickListener
import com.example.newyorktimesbooks.presentation.details.BookDetailsActivity
import com.example.newyorktimesbooks.presentation.viewmodel.BooksViewModel
import com.example.newyorktimesbooks.util.onShowToast
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksActivity : BaseActivity() {

    private val viewModel: BooksViewModel by viewModel()

    private val adapterBooks: BooksAdapter by lazy {
        BooksAdapter(OnclickListener { book ->
            BookDetailsActivity.start(this, book)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbarMain, R.string.books_title)

        initRecyclerView()

        viewModel.msg.observe(this, Observer {
            it?.onShowToast(this)
        })
        viewModel.books.observe(this, Observer {
            it?.let {
                adapterBooks.setList(it)
            }
        })
        viewModel.getBooks()
    }

    private fun initRecyclerView() {
        with(recyclerBooks) {
            setHasFixedSize(true)
            adapter = adapterBooks
        }
    }
}
