package com.wilderapps.costars.presentation.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wilderapps.costars.presentation.Dimens.MediumPadding2
import com.wilderapps.costars.presentation.Dimens.PageIndicatorWidth
import com.wilderapps.costars.presentation.onboarding.components.OnBoardingButton
import com.wilderapps.costars.presentation.onboarding.components.OnBoardingPage
import com.wilderapps.costars.presentation.onboarding.components.OnBoardingTextButton
import com.wilderapps.costars.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit,
    onGetStartedClick: () -> Unit
){
    val darkMode = isSystemInDarkTheme()
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        val pagerState = rememberPagerState(initialPage = 0){
            pages(darkMode).size
        }

        val buttonState = remember{
            derivedStateOf {
                when(pagerState.currentPage){
                    0 -> listOf("", "Next")
                    4 -> listOf("Back", "Get Started")
                    else -> listOf("Back", "Next")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages(darkMode)[index])
        }

        Spacer(modifier = Modifier.weight(1.0f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages(darkMode).size,
                selectedPage = pagerState.currentPage
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                val scope = rememberCoroutineScope()

                if (buttonState.value[0].isNotEmpty()){
                    OnBoardingTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    BackHandler(true) {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    }
                }

                OnBoardingButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 4){
                                event(OnBoardingEvent.SaveAppEntry)
                                onGetStartedClick()

                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
        Spacer(Modifier.weight(0.5f))
    }
}