package com.wilderapps.costars.ui.screens.comparisonScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.wilderapps.costars.R
import com.wilderapps.costars.model.SharedProject
import com.wilderapps.costars.ui.screens.queryScreen.QueryViewModel

@Composable
fun ComparisonScreen(
    viewModel: QueryViewModel){

    Log.d("Testing People", "${viewModel.selectedPeople.size}")
    for(person in viewModel.selectedPeople){
        Log.d("Testing getCredits","${person.name} credits: ${person.credits.size}")
    }
    Log.d("Testing shared credits","Shared Credits: ${viewModel.sharedProjects.size}")
    SharedProjectList(sharedProjects = viewModel.sharedProjects)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedProjectItem(
    sharedProject: SharedProject,
    modifier: Modifier
){
    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxSize(),
        onClick = {

        }) {
        Row(verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(sharedProject.getFullPosterPath())
                    .crossfade(true)
                    .build(),
                contentDescription = sharedProject.title,
                contentScale = ContentScale.Fit,
                error = painterResource(id = R.drawable.broken_image),
                placeholder = painterResource(id = R.drawable.broken_image),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxHeight()
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(sharedProject.title)
            }
        }
    }
}

@Composable
fun SharedProjectList(
    sharedProjects: List<SharedProject>
){
    LazyColumn {
        items(
            items = sharedProjects,
            key = { sharedProject -> sharedProject.id }) {
            sharedProject ->
            SharedProjectItem(
                sharedProject = sharedProject,
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun SharedProjectItemPreview(){
    val sharedProject = SharedProject(
        title = "Twelve Monkeys",
        posterPath = "/gt3iyguaCIw8DpQZI1LIN5TohM2.jpg"
    )

    SharedProjectItem(sharedProject = sharedProject, modifier = Modifier)
}