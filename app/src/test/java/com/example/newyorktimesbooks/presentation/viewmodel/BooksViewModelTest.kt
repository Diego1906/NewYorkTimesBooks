package com.example.newyorktimesbooks.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.data.dto.BooksResult
import com.example.newyorktimesbooks.data.repository.MockRepository
import com.example.newyorktimesbooks.domain.Book
import com.example.newyorktimesbooks.util.HttpStatus
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    private lateinit var viewModel: BooksViewModel

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperObserver: Observer<Pair<Int, Int?>>

    // Testing Success
    @Test
    fun `when viewModel getBooks get success then sets booksLiveDate`() {
        // Arrange
        val books = listOf(
            Book("title 1", "author 1", "description 1")
        )

        viewModel = BooksViewModel(
            MockRepository(BooksResult.Success(books))
        )
        viewModel.books.observeForever(booksObserver)
        viewModel.viewFlipper.observeForever(viewFlipperObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(booksObserver).onChanged(books)
        verify(viewFlipperObserver).onChanged(Pair(VIEW_FLIPPER_BOOKS, null))
    }

    // Testing Untreated error
    @Test
    fun `when viewModel getBooks get untreated error then sets viewFlipper`() {
        // Arrange
        viewModel = BooksViewModel(
            MockRepository(BooksResult.ApiError(HttpStatus.BAD_REQUEST.value, true))
        )
        viewModel.viewFlipper.observeForever(viewFlipperObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperObserver).onChanged(
            Pair(VIEW_FLIPPER_ERROR, R.string.books_error_400_generic)
        )
    }

    // Testing ServerError
    @Test
    fun `when viewModel getBooks get server error then sets viewFlipper`() {
        // Arrange
        viewModel = BooksViewModel(
            MockRepository(BooksResult.ServerError(true))
        )
        viewModel.viewFlipper.observeForever(viewFlipperObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperObserver).onChanged(
            Pair(VIEW_FLIPPER_ERROR, R.string.books_error_500_generic)
        )
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}

