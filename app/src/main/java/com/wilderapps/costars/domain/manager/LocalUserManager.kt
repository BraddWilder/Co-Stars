package com.wilderapps.costars.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}