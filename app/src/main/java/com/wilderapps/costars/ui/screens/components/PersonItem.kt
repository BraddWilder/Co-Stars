package com.wilderapps.costars.ui.screens.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.wilderapps.costars.R
import com.wilderapps.costars.model.Person

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonItem(
    person: Person,
    onPersonClick: (Person) -> Unit,
    modifier: Modifier
){
    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxSize(),
        onClick = {
            onPersonClick(person)
            Log.d("Testing Card Click", "Name: ${person.name}")
            Log.d("Testing Card Click", "Popularity: ${person.popularity}")
            Log.d("Testing Card Click", "Original Name: ${person.originalName}")
            Log.d("Testing Card Click", "Profile Path: ${person.profilePath}")
            Log.d("Testing Card Click", "Full Profile Path: ${person.getFullProfilePath()}")
        }) {
        Row(verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(person.getFullProfilePath())
                    .crossfade(true)
                    .build(),
                contentDescription = person.name,
                contentScale = ContentScale.Fit,
                error = painterResource(id = R.drawable.broken_image),
                placeholder = painterResource(id = R.drawable.broken_image),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxHeight()
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(person.name)
                Text(person.getKnownFor())
            }
        }
    }
}