package com.wilderapps.costars.data

import androidx.annotation.StringRes
import com.wilderapps.costars.R

enum class CostarsScreens(@StringRes val title: Int){
    QueryScreen(title = R.string.query_screen),
    PeopleSelectScreen(title = R.string.people_select_screen),
    ComparisonScreen(title = R.string.comparison_screen),
    ProjectDetailsScreen(title = R.string.project_details_screen),
    AboutScreen(title = R.string.about_screen),
    OnboardingScreen(title = R.string.onboarding),
    HistoryScreen(title = R.string.history)
}