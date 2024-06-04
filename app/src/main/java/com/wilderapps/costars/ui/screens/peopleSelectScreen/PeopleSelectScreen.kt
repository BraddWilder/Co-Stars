package com.wilderapps.costars.ui.screens.peopleSelectScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wilderapps.costars.model.Person
import com.wilderapps.costars.ui.screens.components.PersonItem
import com.wilderapps.costars.ui.screens.queryScreen.QueryViewModel

@Composable
fun PeopleSelectScreen(
    viewModel: QueryViewModel,
    onPersonClick: (Person) -> Unit,
    onCompareClick: () -> Unit
){
//    viewModel.selectedPeople[0] = getDummyPersonOne()
//    viewModel.selectedPeople[1] = getDummyPersonTwo()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn (
            ){
//                items(items = viewModel.selectedPeople, key = {person -> person.id}){
//                    person -> PersonItem(
//                        person = person,
//                        onPersonClick = onPersonClick,
//                        modifier = Modifier
//                            .height(250.dp)
//                    )
//                }
                itemsIndexed(items = viewModel.selectedPeople){
                    index: Int, person: Person -> PersonItem(
                        person = person,
                        onPersonClick = {
                            onPersonClick(person)
                            viewModel.selectedPersonIndex = index
                        },
                        modifier = Modifier
                            .height(250.dp)
                    )
                }
            }
        }
        Button(onClick = onCompareClick) {
            Text("Compare filmography")
        }
    }
}