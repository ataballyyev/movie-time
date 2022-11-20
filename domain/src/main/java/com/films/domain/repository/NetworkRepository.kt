package com.films.domain.repository

import com.films.domain.model.NetworkResult
import com.films.domain.model.SearchResultModel

interface NetworkRepository {

    suspend fun getSearchResults(
        title: String,
        page: Int,
        apiKey: String
    ): NetworkResult<SearchResultModel>

}