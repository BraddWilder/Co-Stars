package com.wilderapps.costars.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.wilderapps.costars.model.Credit
import com.wilderapps.costars.model.Person

data class PersonWithCredits(
    @Embedded val person: Person,
    @Relation(
        parentColumn = "personId",
        entityColumn = "personId"
    )
    val credits: List<Credit>
)
