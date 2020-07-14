package com.example.newyorktimesbooks.presentation.viewmodel

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.application.App
import com.example.newyorktimesbooks.data.mapper.mapToDomain
import com.example.newyorktimesbooks.data.repository.BooksRepository
import com.example.newyorktimesbooks.data.response.BookBodyResponse
import com.example.newyorktimesbooks.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun getBooksWithCoroutines() {
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

    fun getBooksWithCall() {
        repository.getBooksWithCall().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>, response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val temp = mutableListOf<Book>()
                        response.body()?.bookResults?.map { result ->
                            result.details?.map { detail ->
                                temp.add(detail.mapToDomain())
                            }
                        }
                        _books.value = temp
                        setViewFlipper(VIEW_FLIPPER_BOOKS, null)
                    }
                    response.code() == NOT_ALLOWED -> {
                        setViewFlipper(VIEW_FLIPPER_ERROR, R.string.books_error_401)
                    }
                    else -> {
                        setViewFlipper(VIEW_FLIPPER_ERROR, R.string.books_error_400_generic)
                        Timber.log(
                            Log.ERROR, App.getContext().getString(R.string.books_error_400_generic)
                        )
                    }
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                setViewFlipper(VIEW_FLIPPER_ERROR, R.string.books_error_500_generic)
                Timber.log(Log.ERROR, t)
            }
        })
    }

    private fun setViewFlipper(child: Int, @StringRes resId: Int?) {
        _viewFlipper.value = Pair(child, resId)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
        private const val NOT_ALLOWED = 401
    }
}
