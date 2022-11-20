package com.films.data.repository

import android.util.Log
import com.films.data.service.SearchService
import com.films.domain.model.NetworkResult
import com.films.domain.model.SearchResultModel
import com.films.domain.repository.NetworkRepository
import retrofit2.Retrofit
import java.net.SocketTimeoutException
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): NetworkRepository {

    override suspend fun getSearchResults(
        title: String,
        page: Int,
        apiKey: String
    ): NetworkResult<SearchResultModel> {
        return try {
            val response = retrofit
                .create(SearchService::class.java)
                .getSearchResults(title = title, page = page, apiKey = apiKey)
            if (response.code() == 200) {
                Log.i("TAG", "RESPONSE SUCCESS")
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
            NetworkResult.Error(e.message)
        } catch (e: SocketTimeoutException) {
            NetworkResult.Error(e.message)
        }
    }

}