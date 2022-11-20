package com.films.data.service

import com.films.domain.model.SearchResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("")
    suspend fun getSearchResults(
        @Query("s") title: String,
        @Query("page") page: Int = 1,
        @Query("apikey") apiKey: String
    ): Response<SearchResultModel>

}