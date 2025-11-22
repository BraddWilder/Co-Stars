package com.wilderapps.costars.domain.usecases

import com.wilderapps.costars.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }

}