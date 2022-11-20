package com.films.movietime

import android.app.Application
import com.films.movietime.di.AppComponent
import com.films.movietime.di.DaggerAppComponent

class MovieApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}