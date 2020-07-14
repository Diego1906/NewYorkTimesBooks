package com.example.newyorktimesbooks.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.domain.Book
import com.example.newyorktimesbooks.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetailsActivity : BaseActivity() {

    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        setupToolbar(R.string.book_details_title, true)

        initBook(savedInstanceState)

        setFields()
    }

    private fun initBook(savedInstanceState: Bundle?) {
        book = savedInstanceState?.getParcelable(EXTRA_DETAIL) ?: run {
            intent.getParcelableExtra(EXTRA_DETAIL) as Book
        }
    }

    private fun setFields() {
        txtTitleDetail.text = book.title
        txtTitleDescription.text = book.description
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelable(EXTRA_DETAIL, book)
    }

    companion object {
        private const val EXTRA_DETAIL = "book"

        fun start(context: Context, book: Book) {
            context.startActivity(
                Intent(context, BookDetailsActivity::class.java).apply {
                    putExtra(EXTRA_DETAIL, book)
                }
            )
        }
    }
}
