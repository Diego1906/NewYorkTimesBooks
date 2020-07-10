package com.example.newyorktimesbooks.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newyorktimesbooks.data.mapper.mapToDomain
import com.example.newyorktimesbooks.data.model.Book
import com.example.newyorktimesbooks.data.repository.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(private val repository: BooksRepository) : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books
        get() = _books

    fun getBooks() {
        // _books.value = createFakeBooks()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Transformations.map(repository.getBooks()) { body ->
                    body.bookResults.map { results ->
                        _books.postValue(results.details.map { detail ->
                            detail.mapToDomain()
                        })
                    }
                }
            }
        }
    }

    fun createFakeBooks() = listOf(
        Book(title = "Kotlin em ação", description = "The best", author = "Demitriv"),
        Book(title = "Kotlin", description = "New", author = "Desconhecido"),
        Book(title = "Android com Kotlin", description = "Old Book", author = "Ricado Lecheta")
    )

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}