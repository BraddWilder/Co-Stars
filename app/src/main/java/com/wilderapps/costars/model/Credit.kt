package com.wilderapps.costars.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Credit(
    @SerializedName(value = "backdrop_path")
    var backdropPath: String = "",
    var id: Int = 1,
    @SerializedName(value = "original_title")
    var originalTitle: String = "",
    var overview: String = "",
    @SerializedName(value = "poster_path")
    var posterPath: String? = "",
    @SerializedName(value = "media_type")
    var mediaType: String = "",
    var adult: Boolean = false,
    @SerializedName(value = "original_language")
    var originalLanguage: String = "",
    @SerializedName(value = "genre_ids")
    var genreIds: MutableList<Int> = mutableListOf(),
    var popularity: Double = 0.00,
    var video: Boolean = false,
    @SerializedName(value = "vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName(value = "vote_count")
    var voteCount: Double = 0.0,

    //Movie Specific Fields
    @SerializedName(value = "release_date")
    var releaseDate: String = "",
    var title: String = "",

    //TV Specific fields
    var name: String = "",
    @SerializedName(value = "first_air_date")
    var firstAirDate: String = "",

    //Cast Specific Fields
    @SerializedName(value = "character")
    var characterName: String = "",

    //Crew Specific Fields
    var department: String = "",
    var job: String = ""
)