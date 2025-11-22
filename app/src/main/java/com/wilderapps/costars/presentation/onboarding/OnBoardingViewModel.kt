package com.wilderapps.costars.presentation.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilderapps.costars.data.CostarsScreens
import com.wilderapps.costars.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

    var onBoardingCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(CostarsScreens.PeopleSelectScreen.name)
        private set

    init{
        appEntryUseCases.readAppEntry().onEach { shouldStartFromPeopleSelectScreen ->
            if(shouldStartFromPeopleSelectScreen){
                startDestination = CostarsScreens.PeopleSelectScreen.name
            }else{
                startDestination = CostarsScreens.OnboardingScreen.name
            }
            delay(300)
            onBoardingCondition = false
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}