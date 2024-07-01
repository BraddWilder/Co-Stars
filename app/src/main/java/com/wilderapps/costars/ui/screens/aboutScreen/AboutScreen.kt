package com.wilderapps.costars.ui.screens.aboutScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.wilderapps.costars.R

@Composable
fun AboutScreen(){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ){
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("https://www.themoviedb.org/assets/2/v4/logos/v2/blue_long_1-8ba2ac31f354005783fab473602c34c3f4fd207150182061e425d366e4f34596.svg")
                .crossfade(true)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = "The Movie DB",
            contentScale = ContentScale.Fit,
            error = painterResource(id = R.drawable.broken_image),
            placeholder = painterResource(id = R.drawable.person_placeholder),
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxWidth()
        )
        Text(
            text = "This app uses TMDB and the TMDB APIs but is not endorsed, certified, or otherwise approved by TMDB.",
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
        )
        Text(
            text = "Please ensure any issues with data or images are not also found at TMDB before reporting them to ${stringResource(
                id = R.string.app_name_plural_possessive
            )} developers.",
            modifier = Modifier
                .padding(all = 8.dp)
        )
    }
}