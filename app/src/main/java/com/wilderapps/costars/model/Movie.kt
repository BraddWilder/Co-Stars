package com.wilderapps.costars.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName(value = "backdrop_path")
    var backdropPath: String = "",
    var id: Int = 1,
    @SerialName(value = "original_title")
    var originalTitle: String = "",
    var overview: String = "",
    @SerialName(value = "poster_path")
    var posterPath: String = "",
    @SerialName(value = "media_type")
    var mediaType: String = "",
    var adult: Boolean = false,
    var title: String = "",
    @SerialName(value = "original_language")
    var originalLanguage: String = "",
    @SerialName(value = "genre_ids")
    var genreIds: List<Int> = emptyList(),
    var popularity: Double = 0.00,
    @SerialName(value = "release_date")
    var releaseDate: String = "",
    var video: Boolean = false,
    @SerialName(value = "vote_average")
    var voteAverage: Double = 0.0,
    @SerialName(value = "vote_count")
    var voteCount: Double = 0.0
)