package com.wilderapps.costars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.wilderapps.costars.presentation.onboarding.OnBoardingViewModel
import com.wilderapps.costars.ui.CostarsApp
import com.wilderapps.costars.ui.theme.CoStarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<OnBoardingViewModel>()

//    @Inject
//    lateinit var dao: RoomDao
//
//    lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch {
//            dao.upsertCredit(getDummyCredit())
//            dao.upsertPerson(getDummyPersonOne())
//            val personWithCredits = dao.getPersonWithCredits(287)
//            person = personWithCredits[0].person
//            person.credits = personWithCredits[0].credits
//            dao.deleteCreditsByPersonId(287)
//            dao.deletePersonByPersonId(287)
//        }

        setContent {
            CoStarsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        val startDestination = viewModel.startDestination
                        CostarsApp(startDestination = startDestination)
                    }
                }
            }
        }
    }
}
