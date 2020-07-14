package com.example.newyorktimesbooks.presentation.viewmodel

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.application.App
import com.example.newyorktimesbooks.data.BooksResult
import com.example.newyorktimesbooks.data.repository.BooksRepository
import com.example.newyorktimesbooks.domain.Book
import com.example.newyorktimesbooks.util.HttpStatus
import kotlinx.coroutines.cancel
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

    /*
    * USING COROUTINES
    fun getBooks() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val temp = mutableListOf<Book>()
                    repository.getBooks().bookResults?.map { result ->
                        result.details?.map { detail ->
                            temp.add(detail.mapToDomain())
                        }
                    }
                    _books.postValue(temp)
                } catch (throwable: AccessDeniedException) {
                    Timber.e(throwable)

                    // TODO: Mehorar a mensagem de erro: Utilizando strings resources
                    _msg.postValue(throwable.message)
                } catch (throwable: Throwable) {
                    Timber.e(throwable)

                    // TODO: Mehorar a mensagem de erro: Utilizando strings resources
                    _msg.postValue(throwable.message)
                }
            }
        }
    }
     */

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
                        setLog(R.string.books_error_400_generic)
                    }
                }
                is BooksResult.ServerError -> {
                    setViewFlipper(resId = R.string.books_error_500_generic)
                    setLog(R.string.books_error_500_generic)
                }
            }
        }
    }

    private fun setLog(@StringRes resId: Int) {
        Timber.log(Log.ERROR, App.getContext().getString(resId))
    }

    private fun setViewFlipper(child: Int = VIEW_FLIPPER_ERROR, @StringRes resId: Int? = null) {
        _viewFlipper.value = Pair(child, resId)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}
