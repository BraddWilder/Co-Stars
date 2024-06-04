package com.wilderapps.costars.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wilderapps.costars.R
import com.wilderapps.costars.ui.screens.comparisonScreen.ComparisonScreen
import com.wilderapps.costars.ui.screens.peopleSelectScreen.PeopleSelectScreen
import com.wilderapps.costars.ui.screens.queryScreen.QueryScreen
import com.wilderapps.costars.ui.screens.queryScreen.QueryViewModel

enum class CostarsScreens(@StringRes val title: Int){
    QueryScreen(title = R.string.query_screen),
    PeopleSelectScreen(title = R.string.people_select_screen),
    ComparisonScreen(title = R.string.comparison_screen)
}
@Composable
fun CostarsApp(
    viewModel: QueryViewModel = viewModel(factory = QueryViewModel.Factory),
    navController: NavHostController = rememberNavController()
){
    Scaffold {
        innerPadding ->
        //QueryScreen(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
        //PeopleSelectScreen(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
        NavHost(
            navController = navController,
            startDestination = CostarsScreens.PeopleSelectScreen.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = CostarsScreens.PeopleSelectScreen.name){
                PeopleSelectScreen(
                    viewModel = viewModel,
                    onPersonClick = {
                        viewModel.query = it.name
                        viewModel.getPeople()
                        navController.navigate(CostarsScreens.QueryScreen.name)
                    },
                    onCompareClick = {
                        viewModel.getCredits()
                        navController.navigate(CostarsScreens.ComparisonScreen.name)
                    }
                )
            }
            composable(route = CostarsScreens.QueryScreen.name){
                QueryScreen(
                    viewModel = viewModel,
                    onPersonClick = {
                        viewModel.selectedPeople[viewModel.selectedPersonIndex] = it
                        viewModel.query = ""
                        viewModel.getPeople()
                        navController.navigate(CostarsScreens.PeopleSelectScreen.name)
                    },
                    modifier = Modifier
                )
            }
            composable(route = CostarsScreens.ComparisonScreen.name){
                ComparisonScreen(
                    viewModel = viewModel
                )
            }
        }
    }

}