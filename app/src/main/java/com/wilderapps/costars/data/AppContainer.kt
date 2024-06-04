package com.wilderapps.costars.data

import com.wilderapps.costars.network.CostarsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppContainer {
    val costarsRepository: CostarsRepository
}

class DefaultAppContainer: AppContainer{
    private val baseUrl = "https://api.themoviedb.org/3/"

    private val costarsApiService: CostarsApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).build().create()
    }

    override val costarsRepository: CostarsRepository by lazy {
        NetworkCostarsRepository(costarsApiService)
    }
}