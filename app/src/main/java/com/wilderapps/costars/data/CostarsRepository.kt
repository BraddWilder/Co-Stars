package com.wilderapps.costars.data

import com.wilderapps.costars.model.Credit
import com.wilderapps.costars.model.Person
import com.wilderapps.costars.network.CostarsApiService

interface CostarsRepository{
    suspend fun getPeople(query: String): List<Person>?
    suspend fun getCredits(id: String): List<Credit>?
}

class NetworkCostarsRepository(private val costarsApiService: CostarsApiService): CostarsRepository{
    override suspend fun getPeople(query: String): List<Person> {
        val people = mutableListOf<Person>()

        try{
            val res = costarsApiService.getPeople(query)
            if(res.isSuccessful){
                res.body()?.results?.let { people.addAll(it) }
            }
        } catch (e: Exception){
            e.printStackTrace()
        }

        if(people.isNotEmpty()){
            for(i in people){
                var knownForString = ""
                var isMovie: Boolean

                for((index, movie) in i.knownFor.withIndex()){
                    isMovie = movie.mediaType == "movie"
                    if(isMovie) {
                        if (i.knownFor.size == 1)
                            knownForString = movie.title
                        else if (index == i.knownFor.lastIndex)
                            knownForString += "& ${movie.title}"
                        else
                            knownForString += "${movie.title}, "
                    } else {
                        if (i.knownFor.size == 1)
                            knownForString = movie.name
                        else if (index == i.knownFor.lastIndex)
                            knownForString += "& ${movie.name}"
                        else
                            knownForString += "${movie.name}, "
                    }
                }

                i.knownForString = knownForString
            }
        }

        return people
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