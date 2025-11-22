package com.wilderapps.costars.presentation.onboarding.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wilderapps.costars.presentation.Dimens.MediumPadding1
import com.wilderapps.costars.presentation.Dimens.MediumPadding2
import com.wilderapps.costars.presentation.onboarding.Page
import com.wilderapps.costars.presentation.onboarding.pages
import com.wilderapps.costars.ui.theme.CoStarsTheme

@Composable
fun OnBoardingPage(
    page: Page,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier){
        Spacer(modifier = Modifier.height(MediumPadding1))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(415.dp),
            painter = painterResource(id = page.image),
            contentDescription = stringResource(id = page.title),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = stringResource(id = page.title),
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = stringResource(id = page.description),
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingPagePreview(){
    CoStarsTheme {
        OnBoardingPage(page = pages(isSystemInDarkTheme())[0])
    }
}