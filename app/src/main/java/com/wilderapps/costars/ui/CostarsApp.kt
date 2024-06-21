package com.wilderapps.costars.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wilderapps.costars.data.CostarsScreens
import com.wilderapps.costars.ui.screens.comparisonScreen.ComparisonScreen
import com.wilderapps.costars.ui.screens.components.MyTopAppBar
import com.wilderapps.costars.ui.screens.peopleSelectScreen.PeopleSelectScreen
import com.wilderapps.costars.ui.screens.queryScreen.QueryScreen
import com.wilderapps.costars.ui.screens.queryScreen.QueryViewModel


@Composable
fun CostarsApp(
    viewModel: QueryViewModel = viewModel(factory = QueryViewModel.Factory),
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CostarsScreens.valueOf(
        backStackEntry?.destination?.route ?: CostarsScreens.PeopleSelectScreen.name
    )
    val canNavigateBack = navController.previousBackStackEntry != null
    Scaffold (
        topBar = {
            MyTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                onNavigateUpClicked = { navController.navigateUp() })
        }
    ) {
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
                    nameStyle = MaterialTheme.typography.displaySmall,
                    knownForStyle = MaterialTheme.typography.headlineSmall,
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
                        navController.navigateUp()
                    },
                    nameStyle = MaterialTheme.typography.headlineSmall,
                    knownForStyle = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                )
            }
            composable(route = CostarsScreens.ComparisonScreen.name){
                ComparisonScreen(
                    viewModel = viewModel,
//                    textStyle = TextStyle(
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.SemiBold
//                    )
                    textStyle = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }

}