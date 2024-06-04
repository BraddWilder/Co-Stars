package com.wilderapps.costars.network

import com.wilderapps.costars.model.CreditResponse
import com.wilderapps.costars.model.QueryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CostarsApiService {
    @GET("search/person")
    suspend fun getPeople(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: String = "false",
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = "885ff04b51e71c8e16f57918b81f62cd"
    ): Response<QueryResponse>

    @GET("person/{id}/combined_credits")
    suspend fun getCredits(
        @Path("id") id: String,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = "885ff04b51e71c8e16f57918b81f62cd"
    ): Response<CreditResponse>
}