package com.corellidev.covidinfo

import android.app.Application
import com.corellidev.covidinfo.di.dataModule
import com.corellidev.covidinfo.di.domainModule
import com.corellidev.covidinfo.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CovidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CovidApplication)
            modules(listOf(viewModule, domainModule, dataModule))
        }
    }
}