package com.example.newyorktimesbooks.di

import android.os.Debug
import com.example.newyorktimesbooks.data.repository.BooksRepository
import com.example.newyorktimesbooks.data.repository.BooksRepositoryImpl
import com.example.newyorktimesbooks.data.repository.BooksRepositoryTest
import com.example.newyorktimesbooks.data.service.ApiService
import com.example.newyorktimesbooks.data.service.ApiServiceImpl
import com.example.newyorktimesbooks.presentation.viewmodel.BooksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BooksViewModel(repository = get()) }
}

val dataModule = module {
    if (Debug.isDebuggerConnected().not())
        single<BooksRepository> { BooksRepositoryImpl(service = get()) }
    else
        single<BooksRepository> { BooksRepositoryTest(service = get()) }

    single<ApiService> { ApiServiceImpl() }
}