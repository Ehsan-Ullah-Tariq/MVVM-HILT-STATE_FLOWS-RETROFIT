package com.android.test.network

import com.android.test.network.models.HomeDataModelItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("contentItems")
    suspend fun getHomeData(): List<HomeDataModelItem>
}