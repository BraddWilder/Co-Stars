package com.wilderapps.costars.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class QueryResponse(
    val page: Int,
    val results: List<Person>,
    @SerializedName(value = "total_pages")
    val totalPages: Int,
    @SerializedName(value = "total_results")
    val totalResults: Int
)
