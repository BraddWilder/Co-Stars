package com.wilderapps.costars.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wilderapps.costars.R

data class Page(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)

fun pages(darkMode: Boolean): List<Page>{
//    val titles = stringArrayResource(id = R.array.onboarding_title)
//    val descriptions = stringArrayResource(id = R.array.onboarding)
    if(darkMode){
        return listOf(
            Page(
                R.string.onboarding_title_1,
                R.string.onboarding_1,
                R.drawable.darkonboarding1
            ),
            Page(

                R.string.onboarding_title_2,
                R.string.onboarding_2,
                R.drawable.darkonboarding2
            ),
            Page(

                R.string.onboarding_title_3,
                R.string.onboarding_3,
                R.drawable.darkonboarding3
            ),
            Page(

                R.string.onboarding_title_4,
                R.string.onboarding_4,
                R.drawable.darkonboarding4
            ),
            Page(

                R.string.onboarding_title_5,
                R.string.onboarding_5,
                R.drawable.darkonboarding5
            )
        )
    } else {
        return listOf(
            Page(
                R.string.onboarding_title_1,
                R.string.onboarding_1,
                R.drawable.lightonboarding1
            ),
            Page(

                R.string.onboarding_title_2,
                R.string.onboarding_2,
                R.drawable.lightonboarding2
            ),
            Page(

                R.string.onboarding_title_3,
                R.string.onboarding_3,
                R.drawable.lightonboarding3
            ),
            Page(

                R.string.onboarding_title_4,
                R.string.onboarding_4,
                R.drawable.lightonboarding4
            ),
            Page(

                R.string.onboarding_title_5,
                R.string.onboarding_5,
                R.drawable.lightonboarding5
            )
        )
    }
}
