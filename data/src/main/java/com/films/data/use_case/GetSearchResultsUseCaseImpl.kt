package com.films.data.use_case

import com.films.data.repository.NetworkRepositoryImpl
import com.films.domain.model.NetworkResult
import com.films.domain.model.SearchResultModel
import com.films.domain.use_case.GetSearchResultsUseCase
import javax.inject.Inject

class GetSearchResultsUseCaseImpl @Inject constructor(
    private val repositoryImpl: NetworkRepositoryImpl
): GetSearchResultsUseCase {

    override suspend fun getHomeProducts(
        title: String,
        page: Int,
        apiKey: String
    ): NetworkResult<SearchResultModel> {
        return repositoryImpl.getSearchResults(title = title, page = page, apiKey = apiKey)
    }

}