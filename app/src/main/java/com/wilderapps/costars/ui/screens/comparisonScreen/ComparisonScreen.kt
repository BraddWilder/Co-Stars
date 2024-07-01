package com.wilderapps.costars.ui.screens.comparisonScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.wilderapps.costars.R
import com.wilderapps.costars.data.getDummyProject
import com.wilderapps.costars.model.SharedProject
import com.wilderapps.costars.ui.screens.queryScreen.QueryViewModel

@Composable
fun ComparisonScreen(
    viewModel: QueryViewModel,
    textStyle: TextStyle,
    onProjectClick: (SharedProject) -> Unit
){
    if(viewModel.sharedProjects.size == 0){
        NoSharedProjects()
    } else {
        SharedProjectList(
            sharedProjects = viewModel.sharedProjects,
            textStyle = textStyle,
            onProjectClick = onProjectClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedProjectItem(
    sharedProject: SharedProject,
    textStyle: TextStyle,
    onProjectClick: (SharedProject) -> Unit,
    modifier: Modifier
){
    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxSize(),
        onClick = {
            onProjectClick(sharedProject)
        }) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 80.dp)
        ) {
            if(sharedProject.posterPath != null) {
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
                        .padding(start = 8.dp, end = 16.dp)
                        .fillMaxHeight()
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(sharedProject.getFullPosterPath())
                        .crossfade(true)
                        .build(),
                    contentDescription = sharedProject.title,
                    contentScale = ContentScale.Fit,
                    error = painterResource(id = R.drawable.project_placeholder),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .fillMaxHeight()
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text(
                    text = sharedProject.title,
                    style = textStyle)
            }
            if(sharedProject.mediaType == "movie"){
                Icon(painter = painterResource(id = R.drawable.project_placeholder),
                    contentDescription = "Movie")
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.media_type_tv),
                    contentDescription = "TV Series"
                )
            }
        }
    }
}

@Composable
fun SharedProjectList(
    sharedProjects: List<SharedProject>,
    onProjectClick: (SharedProject) -> Unit,
    textStyle:  TextStyle
){
    LazyColumn {
        items(
            items = sharedProjects,
            key = { sharedProject -> sharedProject.id }) {
            sharedProject ->
            SharedProjectItem(
                sharedProject = sharedProject,
                textStyle = textStyle,
                onProjectClick = onProjectClick,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun NoSharedProjects(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        Text(
            text = "There are no shared projects between the people you have selected.",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(8.dp))
    }
}

@Preview
@Composable
fun SharedProjectItemPreview(){
    SharedProjectItem(
        sharedProject = getDummyProject(),
        textStyle = TextStyle(),
        onProjectClick = {},
        modifier = Modifier)
}