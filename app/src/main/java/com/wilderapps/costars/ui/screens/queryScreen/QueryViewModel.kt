package com.wilderapps.costars.ui.screens.queryScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.wilderapps.costars.CostarsApplication
import com.wilderapps.costars.data.CostarsRepository
import com.wilderapps.costars.model.Person
import com.wilderapps.costars.model.SharedProject
import kotlinx.coroutines.launch

sealed interface QueryUiState{
    data class Success(val people: List<Person>): QueryUiState
    object Error: QueryUiState
    object Loading: QueryUiState
}

class QueryViewModel (private val costarsRepository: CostarsRepository): ViewModel(){

    var uiState: QueryUiState by mutableStateOf(QueryUiState.Loading)
        private set
    var query by mutableStateOf("Brad")
//    var firstPerson by mutableStateOf(Person())
//    var secondPerson by mutableStateOf(Person())
    var selectedPeople = mutableStateListOf(Person(id = 123), Person(id = 456))
    var selectedPersonIndex by mutableIntStateOf(0)
    var sharedProjects = mutableStateListOf<SharedProject>()

    fun getPeople(){
        viewModelScope.launch {
            uiState = QueryUiState.Loading
            uiState = try {
                val people = costarsRepository.getPeople(query)
                if(people == null)
                    QueryUiState.Error
                else if (people.isEmpty())
                    QueryUiState.Success(emptyList())
                else
                    QueryUiState.Success(people.sortedByDescending{it.popularity})

            } catch (e: Exception){
                QueryUiState.Error
            }
        }
    }

    fun getCredits(){
        viewModelScope.launch {
            for(person in selectedPeople){
                if(person.credits.isEmpty()) {
                    try {
                        val credits = costarsRepository.getCredits(person.id.toString())
                        if (credits != null)
                            person.credits = credits
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            sharedProjects.clear()
            sharedProjects.addAll(selectedPeople[0].getSharedCredits(selectedPeople[1]))
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CostarsApplication)
                val costarsRepository = application.container.costarsRepository
                QueryViewModel(costarsRepository)
            }
        }
    }
}