package com.films.movietime.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.films.data.use_case.GetSearchResultsUseCaseImpl
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getSearchResultsUseCaseImpl: GetSearchResultsUseCaseImpl,
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                getSearchResultsUseCaseImpl = getSearchResultsUseCaseImpl
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}