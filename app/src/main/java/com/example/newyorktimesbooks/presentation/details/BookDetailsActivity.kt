package com.example.newyorktimesbooks.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.data.model.Book
import kotlinx.android.synthetic.main.activity_book_details.*
import kotlinx.android.synthetic.main.toolbar.*

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        toolbarMain.title = getString(R.string.details_title)
        setSupportActionBar(toolbarMain)

        book = savedInstanceState?.getParcelable(EXTRA_DETAIL) ?: run {
            intent.getParcelableExtra(EXTRA_DETAIL) as Book
        }

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
