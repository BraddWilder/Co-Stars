package com.wilderapps.costars.ui.screens.projectDetailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.wilderapps.costars.R
import com.wilderapps.costars.model.SharedProject

@Composable
fun ProjectDetailsScreen(
    //viewModel: QueryViewModel,
    sharedProject: SharedProject
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()) {
            Text(
                sharedProject.title,
                style = MaterialTheme.typography.displayMedium
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(sharedProject.getFullPosterPath())
                    .crossfade(true)
                    .build(),
                contentDescription = sharedProject.title,
                contentScale = ContentScale.Fit,
                error = painterResource(id = R.drawable.broken_image),
                placeholder = painterResource(id = R.drawable.project_placeholder),
                modifier = Modifier
                    .padding(8.dp)
                    .height(300.dp)
            )
        }
        Row() {
           Text(
               text = "Release date:",
               style = MaterialTheme.typography.bodyMedium,
               fontWeight = FontWeight.SemiBold,
               modifier = Modifier
                   .padding(start = 8.dp, end = 8.dp)
           )
            Text(
                text = sharedProject.releaseDate,
                style = MaterialTheme.typography.bodyMedium)
        }
        Text(
            text = sharedProject.summary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}