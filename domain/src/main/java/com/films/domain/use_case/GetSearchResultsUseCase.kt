package com.films.domain.use_case

import com.films.domain.model.NetworkResult
import com.films.domain.model.SearchResultModel

interface GetSearchResultsUseCase {

    suspend fun getHomeProducts(
        title: String,
        page: Int,
        apiKey: String
    ): NetworkResult<SearchResultModel>

}