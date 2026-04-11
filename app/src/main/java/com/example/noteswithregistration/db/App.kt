package com.example.noteswithregistration.db

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.noteswithregistration.DI.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import kotlin.jvm.java

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}