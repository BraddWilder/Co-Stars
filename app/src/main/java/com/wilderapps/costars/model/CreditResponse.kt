package com.wilderapps.costars.model

data class CreditResponse(
    val cast: List<Credit>,
    val crew: List<Credit>,
    val id: Int
)
