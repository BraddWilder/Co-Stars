package com.wilderapps.costars.di

import android.app.Application
import androidx.room.Room
import com.wilderapps.costars.data.manager.LocalUserManagerImpl
import com.wilderapps.costars.domain.manager.LocalUserManager
import com.wilderapps.costars.domain.usecases.AppEntryUseCases
import com.wilderapps.costars.domain.usecases.ReadAppEntry
import com.wilderapps.costars.domain.usecases.SaveAppEntry
import com.wilderapps.costars.local.CoStarsDatabase
import com.wilderapps.costars.local.RoomDao
import com.wilderapps.costars.local.typeConverters.GenreIdTypeConverter
import com.wilderapps.costars.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideCoStarsDatabase(
        application: Application
    ): CoStarsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = CoStarsDatabase::class.java,
            name = DATABASE_NAME
        ).addTypeConverter(GenreIdTypeConverter())
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomDao(
        coStarsDatabase: CoStarsDatabase
    ): RoomDao = coStarsDatabase.roomDao
}