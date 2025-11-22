package com.wilderapps.costars

import android.app.Application
import com.wilderapps.costars.data.AppContainer
import com.wilderapps.costars.data.DefaultAppContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CostarsApplication: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}