package com.wilderapps.costars.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    var adult: Boolean = false,
    var gender: Int = 1,
    var id: Int = 1,
    @SerialName(value = "known_for_department")
    var knownForDepartment: String = "",
    var name: String = "",
    @SerialName(value = "original_name")
    var originalName: String = "",
    var popularity: Double = 0.00,
    @SerialName(value = "profile_path")
    var profilePath: String = "",
    @SerialName(value = "known_for")
    var knownFor: List<Movie> = emptyList()
    )