package com.wilderapps.costars.model

data class HistorySearch (
    var id: Int = 1,
    var people: List<Person> = emptyList()
){
}