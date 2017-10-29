package ch.whattowat.android.dagger

import android.content.Context
import ch.whattowat.android.WhatToWatchApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: WhatToWatchApplication) {

    @Provides
    fun providesApplication() = application

    @Provides
    fun providesContext(): Context = application
}