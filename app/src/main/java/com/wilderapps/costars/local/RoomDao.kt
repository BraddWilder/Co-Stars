package com.wilderapps.costars.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.wilderapps.costars.local.relations.PersonWithCredits
import com.wilderapps.costars.model.Credit
import com.wilderapps.costars.model.Person

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCredit(credit: Credit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPerson(person: Person)

    @Transaction
    @Query("SELECT * FROM person WHERE personId = :personId")
    suspend fun getPersonWithCredits(personId: Int): List<PersonWithCredits>

    @Transaction
    @Query("DELETE FROM credit WHERE personId = :personId")
    suspend fun deleteCreditsByPersonId(personId: Int)

    @Transaction
    @Query("DELETE FROM person WHERE personId = :personId")
    suspend fun deletePersonByPersonId(personId: Int)
}