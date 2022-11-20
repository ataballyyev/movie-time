package com.films.domain.model

data class SearchResultModel(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)