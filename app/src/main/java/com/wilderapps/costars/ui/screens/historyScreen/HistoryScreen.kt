package com.wilderapps.costars.ui.screens.historyScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wilderapps.costars.model.HistorySearch
import com.wilderapps.costars.model.Person
import com.wilderapps.costars.ui.screens.components.PersonRow

@Composable
fun HistoryScreen(
    historyList: List<HistorySearch>,
    onHistoryClick: () -> Unit = { Log.d("HistoryScreen", "Card clicked") },
    modifier: Modifier
){
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier.fillMaxSize()
//    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            items(items = historyList, key = { search -> search.id }){
                search -> HistoryCard(
                    search.people,
                    onHistoryClick = onHistoryClick,
                    modifier
                )
            }
        }
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCard(
    peopleList: List<Person>,
    onHistoryClick: () -> Unit,
    modifier: Modifier
){
    Card(
        onClick = onHistoryClick,
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ){
//        LazyColumn(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier.wrapContentSize()
//        ){
//            items(items = peopleList, key = { person -> person.id }){
//                person -> PersonItem(
//                    person = person,
//                    onPersonClick = {},
//                    nameStyle = MaterialTheme.typography.titleMedium,
//                    knownForStyle = MaterialTheme.typography.bodyMedium,
//                    modifier = modifier
//                )
//            }
//        }
        for(person in peopleList){
            PersonRow(
                person = person,
                nameStyle = MaterialTheme.typography.headlineSmall,
                knownForStyle = MaterialTheme.typography.bodyMedium,
                modifier = modifier
            )
        }
    }
}