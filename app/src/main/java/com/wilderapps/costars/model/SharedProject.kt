package com.wilderapps.costars.model

data class SharedProject(
    var id: Int = 0,
    var title: String = "",
    var posterPath: String? = "",
    var popularity: Double = 0.00,
    var releaseDate: String = "",
    var summary: String = ""
    /*var people: MutableList<Person> = mutableListOf()*/
){
    fun getFullPosterPath(): String{
        return "https://image.tmdb.org/t/p/w185${this.posterPath}"
    }
}
