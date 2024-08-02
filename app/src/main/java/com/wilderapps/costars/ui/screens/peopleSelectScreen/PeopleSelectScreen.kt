package com.wilderapps.costars.ui.screens.peopleSelectScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.google.android.gms.ads.AdSize
import com.wilderapps.costars.model.Person
import com.wilderapps.costars.ui.screens.components.BannerAd
import com.wilderapps.costars.ui.screens.components.PersonItem
import com.wilderapps.costars.ui.screens.queryScreen.QueryViewModel

@Composable
fun PeopleSelectScreen(
    viewModel: QueryViewModel,
    onPersonClick: (Person) -> Unit,
    onCompareClick: () -> Unit,
    onClearClick: () -> Unit,
    nameStyle: TextStyle,
    knownForStyle: TextStyle,
    onDeleteClick: (Person) -> Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn (
            ){
                itemsIndexed(items = viewModel.selectedPeople){
                    index: Int, person: Person -> PersonItem(
                        person = person,
                        onPersonClick = {
                            onPersonClick(person)
                            viewModel.selectedPersonIndex = index
                        },
                        nameStyle = nameStyle,
                        knownForStyle = knownForStyle,
                        isDeleteAvailable = true,
                        onDeleteClick = {
                            onDeleteClick(person)
                        },
                        modifier = Modifier
                            .height(250.dp)
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)) {
                var isCompareAvailable = false
                var isClearAvailable = false
                if(viewModel.selectedPeople.size > 1) {
                    isCompareAvailable = true
                }
                if(viewModel.selectedPeople.size > 0){
                    isClearAvailable = true
                }

                Button(
                    onClick = onClearClick,
                    enabled = isClearAvailable
                ){
                    Text("Clear Selected People")
                }

                Button(
                    onClick = onCompareClick,
                    enabled = isCompareAvailable
                ) {
                    Text("Compare filmography")
                }
            }
        }

        Row(
            modifier = Modifier.height(AdSize.BANNER.height.dp)
        ) {
            BannerAd()
        }
    }
}