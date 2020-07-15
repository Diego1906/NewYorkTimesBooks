package com.example.newyorktimesbooks.presentation.viewmodel

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.application.App
import com.example.newyorktimesbooks.data.dto.BooksResult
import com.example.newyorktimesbooks.data.repository.BooksRepository
import com.example.newyorktimesbooks.domain.Book
import com.example.newyorktimesbooks.util.HttpStatus
import timber.log.Timber

class BooksViewModel(private val repository: BooksRepository) : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books
        get() = _books

    private val _msg = MutableLiveData<String>()
    val msg
        get() = _msg

    private val _viewFlipper = MutableLiveData<Pair<Int, @StringRes Int?>>()
    val viewFlipper
        get() = _viewFlipper

    fun getBooks() {
        repository.getBooks { result: BooksResult ->
            when (result) {
                is BooksResult.Success -> {
                    _books.value = result.books
                    setViewFlipper(child = VIEW_FLIPPER_BOOKS)
                }
                is BooksResult.ApiError -> {
                    if (result.statusCode == HttpStatus.UNAUTHORIZED.value) {
                        setViewFlipper(resId = R.string.books_error_401)
                    } else {
                        setViewFlipper(resId = R.string.books_error_400_generic)
                        checkIsTestMode(result.isTest, R.string.books_error_400_generic)
                    }
                }
                is BooksResult.ServerError -> {
                    setViewFlipper(resId = R.string.books_error_500_generic)
                    checkIsTestMode(result.isTest, R.string.books_error_500_generic)
                }
            }
        }
    }

    private fun checkIsTestMode(isTest: Boolean, @StringRes resId: Int) {
        if (!isTest)
            setLogError(resId)
    }

    private fun setLogError(@StringRes resId: Int) {
        Timber.log(Log.ERROR, App.getContext().getString(resId))
    }

    private fun setViewFlipper(child: Int = VIEW_FLIPPER_ERROR, @StringRes resId: Int? = null) {
        _viewFlipper.value = Pair(child, resId)
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}
