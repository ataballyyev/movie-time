package com.films.movietime.di

import com.films.data.di.NetworkModule
import com.films.movietime.presentation.view.MainFragment
import com.films.movietime.presentation.view.SearchFilterFragment
import dagger.Component

@Component(
    modules = [ NetworkModule::class ]
)
interface AppComponent {

    fun inject(fragment: SearchFilterFragment)
    fun inject(fragment: MainFragment)

}