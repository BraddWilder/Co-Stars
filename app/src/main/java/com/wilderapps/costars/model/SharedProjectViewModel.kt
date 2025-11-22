package com.wilderapps.costars.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class SharedProjectViewModel: ViewModel(){
    private val _sharedProject = mutableStateOf(SharedProject())
    val sharedProject = _sharedProject

    fun getFullPosterPath(): String{
        return "https://image.tmdb.org/t/p/w185${_sharedProject.value.posterPath}"
    }

    fun compareCredits(sharedProjects: List<SharedProject>, person: Person): List<SharedProject>{

        // Award shows are stored as a tag not genre id on TMDB.  To save on API calls this list was
        // manually stored for removal
        val awardShowIds = listOf(30048, 71932, 54328, 206296, 76065, 27023, 71494, 46732, 23521,
            76084, 14598, 96040, 85403, 96054, 16730, 85506, 13667, 40292, 42333, 23608, 37507,
            75466, 139254, 68557, 36268, 104498, 66379, 96038, 90442, 28464, 230983, 38600, 75493,
            75102, 26183, 101602, 23318, 60971, 240481, 27562, 128427, 80480, 209121, 113356, 49175,
            75720, 9259, 203296, 89823, 110770, 210637, 218848, 96499, 25230, 138299, 14498, 67079,
            86305, 25298, 19856, 66138, 58095, 94051, 8768, 200687, 92701, 202070, 60536, 207779,
            135948, 153255, 74714, 221207
        )
        val sharedCredits = emptyList<SharedProject>().toMutableList()
        var isSharedProject = false
        var isBlocked = false

        for(sharedProject in sharedProjects){
            for(credit in person.credits){
                if(sharedProject.id == credit.id){
                    isSharedProject = true
                    if(credit.genreIds.contains(10767)){
                        isBlocked = true
                    }
                    if(credit.genreIds.contains(10763)){
                        isBlocked = true
                    }
                    if(sharedCredits.contains(sharedProject)){
                        isBlocked = true
                    }
                    if(awardShowIds.contains(credit.id)){
                        isBlocked = true
                    }
                }
            }
            if(isSharedProject && !isBlocked){
                sharedCredits.add(sharedProject)
            }
            isBlocked = false
            isSharedProject = false
        }

        return sharedCredits
    }

    fun createSharedProjectFromCredit(credit: Credit): SharedProject{
        val sharedProject = SharedProject(
            id = credit.id,
            creditId = "${credit.id}${credit.mediaType}",
            posterPath = credit.posterPath,
            popularity = credit.popularity,
            summary = credit.overview,
            mediaType = credit.mediaType)
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
