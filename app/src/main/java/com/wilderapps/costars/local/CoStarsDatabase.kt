package com.wilderapps.costars.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wilderapps.costars.local.typeConverters.GenreIdTypeConverter
import com.wilderapps.costars.model.Credit
import com.wilderapps.costars.model.Person

@Database(
    entities = [
        Person::class,
        Credit::class
    ],
    version = 1
)
@TypeConverters(GenreIdTypeConverter::class)

abstract class CoStarsDatabase: RoomDatabase() {

    abstract val roomDao: RoomDao
}