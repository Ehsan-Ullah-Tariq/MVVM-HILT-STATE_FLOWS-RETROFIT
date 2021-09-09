package com.android.test.repositories

import com.android.test.network.ApiService
import com.android.test.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HomeRepository @Inject constructor(private var apiService: ApiService) : BaseRepository() {

    suspend fun getHomeResponse() = safeApiCall {
        apiService.getHomeData()
    }

}