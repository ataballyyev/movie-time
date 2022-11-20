package com.films.movietime.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.films.data.use_case.GetSearchResultsUseCaseImpl
import com.films.domain.model.NetworkResult
import com.films.domain.model.SearchResultModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSearchResultsUseCaseImpl: GetSearchResultsUseCaseImpl,
): ViewModel() {

    private var mSearchResults = MutableLiveData<NetworkResult<SearchResultModel>>()
    val liveSearchResults: LiveData<NetworkResult<SearchResultModel>> get() = mSearchResults

    fun getSearchResults(
        title: String,
        page: Int,
        apiKey: String
    ) {
        viewModelScope.launch {
            mSearchResults.value = getSearchResultsUseCaseImpl.getHomeProducts(
                title = title,
                page = page,
                apiKey = apiKey
            )
        }
    }

}