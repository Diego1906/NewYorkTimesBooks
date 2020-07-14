package com.example.newyorktimesbooks.presentation.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.presentation.adapter.BooksAdapter
import com.example.newyorktimesbooks.presentation.adapter.OnclickListener
import com.example.newyorktimesbooks.presentation.ui.base.BaseActivity
import com.example.newyorktimesbooks.presentation.viewmodel.BooksViewModel
import com.example.newyorktimesbooks.util.onShowToast
import kotlinx.android.synthetic.main.activity_books.*
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

        setupToolbar(R.string.books_title)

        initRecyclerView()

        viewModel.msg.observe(this, Observer {
            it?.onShowToast(this)
        })
        viewModel.books.observe(this, Observer {
            it?.let {
                adapterBooks.setList(it)
            }
        })

        viewModel.viewFlipper.observe(this, Observer {
            it?.let { flipper ->
                viewFlipperBooks.displayedChild = flipper.first
                flipper.second?.let { resId ->
                    txtError.text = getString(resId)
                }
            }
        })

        // viewModel.getBooksWithCoroutines()
        viewModel.getBooksWithCall()
    }

    private fun initRecyclerView() {
        with(recyclerBooks) {
            setHasFixedSize(true)
            adapter = adapterBooks
        }
    }
}
