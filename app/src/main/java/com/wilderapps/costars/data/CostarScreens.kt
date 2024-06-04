package com.wilderapps.costars.data

import androidx.annotation.StringRes
import com.wilderapps.costars.R

enum class CostarsScreens(@StringRes val title: Int){
    QueryScreen(title = R.string.query_screen),
    PeopleSelectScreen(title = R.string.people_select_screen),
    ComparisonScreen(title = R.string.comparison_screen)
}