package com.wilderapps.costars.data

import com.wilderapps.costars.model.Credit
import com.wilderapps.costars.model.Person
import com.wilderapps.costars.network.CostarsApiService

interface CostarsRepository{
    suspend fun getPeople(query: String): List<Person>?
    suspend fun getCredits(id: String): List<Credit>?
}

class NetworkCostarsRepository(private val costarsApiService: CostarsApiService): CostarsRepository{
    override suspend fun getPeople(query: String): List<Person>? {
        return try{
            val res = costarsApiService.getPeople(query)
            if(res.isSuccessful){
                res.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    override suspend fun getCredits(id: String): List<Credit>{
        val fullCredits = emptyList<Credit>().toMutableList()

        try{
            val res = costarsApiService.getCredits(id)
            if(res.isSuccessful){
                res.body()?.cast?.let { fullCredits.addAll(it) }
                res.body()?.crew?.let { fullCredits.addAll(it) }
            }
        } catch (e: Exception){
            e.printStackTrace()
        }

        return fullCredits
    }
}