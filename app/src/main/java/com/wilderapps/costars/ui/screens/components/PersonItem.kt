package com.wilderapps.costars.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
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
    nameStyle: TextStyle,
    knownForStyle: TextStyle,
    isDeleteAvailable: Boolean = false,
    onDeleteClick: (Person) -> Unit = {},
    modifier: Modifier
){
    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxSize(),
        onClick = {
            onPersonClick(person)
        }) {
        Row(verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            if(person.profilePath != "") {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(person.getFullProfilePath())
                        .crossfade(true)
                        .build(),
                    contentDescription = person.name,
                    contentScale = ContentScale.Fit,
                    error = painterResource(id = R.drawable.broken_image),
                    placeholder = painterResource(id = R.drawable.person_placeholder),
                    modifier = modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .fillMaxHeight()
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(person.getFullProfilePath())
                        .crossfade(true)
                        .build(),
                    contentDescription = person.name,
                    contentScale = ContentScale.Fit,
                    error = painterResource(id = R.drawable.person_placeholder),
                    modifier = modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .fillMaxHeight()
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .fillMaxHeight()
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()) {
                    Text(
                        text = person.name,
                        style = nameStyle,
                        modifier = Modifier
                            .weight(1f)
                    )
                    if(isDeleteAvailable) {
                        IconButton(onClick = { onDeleteClick(person) }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete person"
                            )
                        }
                    }
                }
                Text(
                    text = person.getKnownFor(),
                    style = knownForStyle,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }
}