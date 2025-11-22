package com.wilderapps.costars.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wilderapps.costars.data.CostarsScreens
import com.wilderapps.costars.data.getDummyHistorySearch
import com.wilderapps.costars.presentation.onboarding.OnBoardingScreen
import com.wilderapps.costars.presentation.onboarding.OnBoardingViewModel
import com.wilderapps.costars.ui.screens.aboutScreen.AboutScreen
import com.wilderapps.costars.ui.screens.comparisonScreen.ComparisonScreen
import com.wilderapps.costars.ui.screens.components.MyTopAppBar
import com.wilderapps.costars.ui.screens.historyScreen.HistoryScreen
import com.wilderapps.costars.ui.screens.peopleSelectScreen.PeopleSelectScreen
import com.wilderapps.costars.ui.screens.projectDetailsScreen.ProjectDetailsScreen
import com.wilderapps.costars.ui.screens.queryScreen.QueryScreen
import com.wilderapps.costars.ui.screens.queryScreen.QueryViewModel


@Composable
fun CostarsApp(
    viewModel: QueryViewModel = viewModel(factory = QueryViewModel.Factory),
    navController: NavHostController = rememberNavController(),
    startDestination: String
){

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CostarsScreens.valueOf(
        backStackEntry?.destination?.route ?: CostarsScreens.PeopleSelectScreen.name
    )
    val canNavigateBack = navController.previousBackStackEntry != null
    val noEnterTransition : AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        fadeIn(
            animationSpec = tween(durationMillis = 300),
            initialAlpha = 0.99f
        )
    }
    val noExitTransition : AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        fadeOut(
            animationSpec = tween(durationMillis = 300),
            targetAlpha = 0.99f
        )
    }
    Scaffold (
        topBar = {
            if(currentScreen != CostarsScreens.OnboardingScreen) {
                MyTopAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = canNavigateBack,
                    onNavigateUpClicked = { navController.navigateUp() },
                    onAboutClicked = { navController.navigate(CostarsScreens.AboutScreen.name) },
                    onGettingStartedClicked = { navController.navigate(CostarsScreens.OnboardingScreen.name)},
                    onAddClicked = {
                        if (backStackEntry!!.lifecycleIsResumed()) {
                            viewModel.selectedPersonIndex = -1
                            navController.navigate(CostarsScreens.QueryScreen.name)
                        }
                    },
                    onHistoryClicked = { navController.navigate(CostarsScreens.HistoryScreen.name) })
            }
        }
    ) {
        innerPadding ->
        //QueryScreen(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
        //PeopleSelectScreen(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = CostarsScreens.PeopleSelectScreen.name){from: NavBackStackEntry ->
                PeopleSelectScreen(
                    viewModel = viewModel,
                    onPersonClick = {
                        if(from.lifecycleIsResumed()) {
                            viewModel.query = it.name
                            viewModel.getPeople()
                            navController.navigate(CostarsScreens.QueryScreen.name)
                        }
                    },
                    nameStyle = MaterialTheme.typography.headlineMedium,
                    knownForStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal, fontSize = 18.sp),
                    onCompareClick = {
                        if(from.lifecycleIsResumed()) {
                            viewModel.compareCredits()
                            navController.navigate(CostarsScreens.ComparisonScreen.name)
                        }
                    },
                    onClearClick = {
                        if(from.lifecycleIsResumed()) {
                            viewModel.selectedPeople.clear()
                        }
                    },
                    onDeleteClick = {
                        if(from.lifecycleIsResumed()) {
                            viewModel.selectedPeople.remove(it)
                        }
                    }
                )
            }
            composable(route = CostarsScreens.QueryScreen.name){from: NavBackStackEntry ->
                QueryScreen(
                    viewModel = viewModel,
                    onPersonClick = {
                        if(from.lifecycleIsResumed()) {
                            if (viewModel.selectedPersonIndex != -1) {
                                viewModel.selectedPeople[viewModel.selectedPersonIndex] = it
                            } else {
                                viewModel.selectedPeople.add(it)
                            }
                            viewModel.query = ""
                            viewModel.getPeople()
                            navController.navigateUp()
                        }
                    },
                    nameStyle = MaterialTheme.typography.titleLarge,
                    knownForStyle = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
            }
            composable(route = CostarsScreens.ComparisonScreen.name){ from: NavBackStackEntry ->
                ComparisonScreen(
                    viewModel = viewModel,
                    textStyle = MaterialTheme.typography.headlineSmall,
                    onProjectClick = {
                        if(from.lifecycleIsResumed()) {
                            viewModel.selectedSharedProject = it
                            navController.navigate(CostarsScreens.ProjectDetailsScreen.name)
                        }
                    }
                )
            }
            composable(route = CostarsScreens.ProjectDetailsScreen.name){
                ProjectDetailsScreen(
                    viewModel = viewModel,
                    sharedProject = viewModel.selectedSharedProject,
                    nameStyle = MaterialTheme.typography.headlineSmall,
                    creditStyle = MaterialTheme.typography.titleMedium
                )
            }
            composable(route = CostarsScreens.AboutScreen.name){
                AboutScreen()
            }
            composable(route = CostarsScreens.OnboardingScreen.name){
                val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = onBoardingViewModel::onEvent,
                    onGetStartedClick = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = CostarsScreens.HistoryScreen.name){
                HistoryScreen(
                    getDummyHistorySearch(),
                    modifier = Modifier
                )
            }
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

