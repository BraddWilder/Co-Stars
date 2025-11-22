package com.wilderapps.costars.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class PersonViewModel: ViewModel() {
    private val _person = mutableStateOf(Person())
    val person: State<Person> = _person

    fun getFullProfilePath(): String{
        return "https://image.tmdb.org/t/p/w185${_person.value.profilePath}"
    }
    
    fun getCreditNames(creditId: Int): String{
        var creditNames = ""

        for(credit in _person.value.credits){
            if(credit.id == creditId){
                if(creditNames.isEmpty()){
                    creditNames = credit.characterName.ifEmpty {
                        credit.job
                    }
                } else {
                    creditNames += if(credit.characterName.isEmpty()){
                        ", ${credit.job}"
                    } else {
                        ", ${credit.characterName}"
                    }
                }
            }
        }

        return creditNames
    }
}