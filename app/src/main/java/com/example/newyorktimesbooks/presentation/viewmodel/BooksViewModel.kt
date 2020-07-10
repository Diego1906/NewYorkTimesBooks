package com.example.newyorktimesbooks.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newyorktimesbooks.data.model.Book

class BooksViewModel : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books
        get() = _books

    fun getBooks() {
        _books.value = createFakeBooks()
    }

    fun createFakeBooks() = listOf(
        Book(title = "Kotlin em ação", author = "Demitriv"),
        Book(title = "Kotlin", author = "Desconhecido"),
        Book(title = "Android com Kotlin", author = "Ricado Lecheta")
    )
}