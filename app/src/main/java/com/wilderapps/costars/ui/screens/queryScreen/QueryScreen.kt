package com.wilderapps.costars.ui.screens.queryScreen

import android.view.KeyEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.wilderapps.costars.model.Person
import com.wilderapps.costars.ui.screens.components.PersonItem


@Composable
fun PersonList(
    people: List<Person>,
    onPersonClick: (Person) -> Unit,
    nameStyle: TextStyle,
    knownForStyle: TextStyle,
    modifier: Modifier
){
    LazyColumn {
        items(items = people, key = {person -> person.id}){
            person -> PersonItem(
                person = person,
                onPersonClick = onPersonClick,
                nameStyle = nameStyle,
                knownForStyle = knownForStyle,
                modifier = modifier
                    .height(150.dp)
            )
        }
    }

//    LazyVerticalGrid(
//        columns = GridCells.Fixed(1)
//    ) {
//        items(items = people, key = {person -> person.id}){
//            person -> GridItem(person = person, modifier = modifier)
//        }
//    }
}

@Composable
fun QueryScreen(
    viewModel: QueryViewModel,
    onPersonClick: (Person) -> Unit,
    nameStyle: TextStyle,
    knownForStyle: TextStyle,
    modifier: Modifier
){
    val uiState = viewModel.uiState
    Column() {
        OutlinedTextField(
            value = viewModel.query,
            onValueChange = { viewModel.query = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.getPeople()

                }
            ),
            modifier = Modifier
                .onKeyEvent { e ->
                    if (e.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                        viewModel.getPeople()

                    }
                    false
                }
                .fillMaxWidth()
                .padding(8.dp)
        )

        when (uiState) {
            is QueryUiState.Loading -> {}
            is QueryUiState.Success -> PersonList(
                people = uiState.people,
                onPersonClick = onPersonClick,
                nameStyle = nameStyle,
                knownForStyle = knownForStyle,
                modifier = modifier
            )

            is QueryUiState.Error -> {}
        }
    }
}