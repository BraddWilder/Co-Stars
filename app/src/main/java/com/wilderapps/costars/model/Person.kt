package com.wilderapps.costars.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    var adult: Boolean = false,
    var gender: Int = 1,
    var id: Int = 1,
    @SerializedName(value = "known_for_department")
    var knownForDepartment: String = "",
    var name: String = "",
    @SerializedName(value = "original_name")
    var originalName: String = "",
    var popularity: Double = 0.00,
    @SerializedName(value = "profile_path")
    var profilePath: String = "",
    @SerializedName(value = "known_for")
    var knownFor: List<Credit> = emptyList(),
    var credits: List<Credit> = emptyList()
    ){
    fun getKnownFor(): String{
        var knownForString = ""
        var isMovie: Boolean

        for((index, movie) in knownFor.withIndex()){
            isMovie = movie.mediaType == "movie"
            if(isMovie) {
                if (knownFor.size == 1)
                    knownForString = movie.title
                else if (index == knownFor.size - 1)
                    knownForString += "& ${movie.title}"
                else
                    knownForString += "${movie.title}, "
            } else {
                if (knownFor.size == 1)
                    knownForString = movie.name
                else if (index == knownFor.size - 1)
                    knownForString += "& ${movie.name}"
                else
                    knownForString += "${movie.name}, "
            }
        }

        return knownForString
    }

    fun getFullProfilePath(): String{
        return "https://image.tmdb.org/t/p/w185${this.profilePath}"
    }

    fun getSharedCredits(person: Person): List<SharedProject>{
        val sharedCredits = emptyList<SharedProject>().toMutableList()

        for(credit in credits){
            for(otherCredit in person.credits){
                if(otherCredit.id == credit.id){
                    var addProject = true
                    val sharedProject = createSharedCredit(otherCredit)
                    if(otherCredit.genreIds.contains(10767)) {
                        addProject = false
                    }
                    if(otherCredit.genreIds.contains(10763)){
                        addProject = false
                    }
                    if(addProject && !sharedCredits.contains(sharedProject))
                        sharedCredits.add(sharedProject)
                }
            }
        }

        return sharedCredits
    }

    fun createSharedCredit(credit: Credit): SharedProject{
        val sharedProject = SharedProject(
            id = credit.id,
            posterPath = credit.posterPath,
            popularity = credit.popularity,
            summary = credit.overview)
        if(credit.mediaType == "tv") {
            sharedProject.title = credit.name
            sharedProject.releaseDate = credit.firstAirDate
        }
        else {
            sharedProject.title = credit.title
            sharedProject.releaseDate = credit.releaseDate
        }

        return sharedProject
    }
}