package com.wilderapps.costars.ui.screens.queryScreen

import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.wilderapps.costars.R
import com.wilderapps.costars.model.Person
import com.wilderapps.costars.ui.screens.components.ErrorScreen
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
        items(items = people, key = {person -> person.personId}){
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
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun QueryScreen(
    viewModel: QueryViewModel,
    onPersonClick: (Person) -> Unit,
    nameStyle: TextStyle,
    knownForStyle: TextStyle,
    modifier: Modifier
){
    val uiState = viewModel.uiState
    val controller = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val requester = FocusRequester()
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
                        startSearch(viewModel, controller!!, focusManager)
                    }
                ),
                modifier = Modifier
                    .onKeyEvent { e ->
                        if (e.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                            startSearch(viewModel, controller!!, focusManager)
                        }
                        false
                    }
                    .fillMaxWidth()
                    .padding(8.dp)
                    .focusRequester(requester)
                    .weight(1f)
            )
            Button(
                onClick = { startSearch(viewModel, controller!!, focusManager) },
                modifier = Modifier.wrapContentSize()
                ){
                if(isSystemInDarkTheme()) {
                    Image(
                        painterResource(id = R.drawable.search_dark_mode),
                        contentDescription = "Search"
                    )
                } else {
                    Image(
                        painterResource(id = R.drawable.search_light_mode),
                        contentDescription = "Search"
                    )
                }
            }
            LaunchedEffect(Unit) {
                requester.requestFocus()
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            when (uiState) {
                is QueryUiState.Loading -> {}
                is QueryUiState.Success -> PersonList(
                    people = uiState.people,
                    onPersonClick = onPersonClick,
                    nameStyle = nameStyle,
                    knownForStyle = knownForStyle,
                    modifier = modifier
                )

                is QueryUiState.Error -> ErrorScreen { viewModel.getPeople() }
            }
        }

//        BannerAd()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun startSearch(
    viewModel: QueryViewModel,
    controller: SoftwareKeyboardController,
    focusManager: FocusManager
){
    viewModel.getPeople()
    controller.hide()
    focusManager.clearFocus()
}