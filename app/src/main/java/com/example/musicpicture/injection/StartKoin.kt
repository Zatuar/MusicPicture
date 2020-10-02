package com.example.musicpicture.injection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class StartKoin : Application() {

    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@StartKoin)
            modules(presentationKoin)
        }
    }
}