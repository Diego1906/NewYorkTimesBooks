package com.example.newyorktimesbooks.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newyorktimesbooks.data.mapper.mapToDomain
import com.example.newyorktimesbooks.data.model.Book
import com.example.newyorktimesbooks.data.repository.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class BooksViewModel(private val repository: BooksRepository) : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books
        get() = _books

    private val _msg = MutableLiveData<String>()
    val msg
        get() = _msg

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

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}