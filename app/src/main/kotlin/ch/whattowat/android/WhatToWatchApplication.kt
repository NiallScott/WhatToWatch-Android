package ch.whattowat.android

import android.app.Application
import ch.whattowat.android.dagger.ApplicationComponent
import ch.whattowat.android.dagger.ApplicationModule
import ch.whattowat.android.dagger.DaggerApplicationComponent

class WhatToWatchApplication : Application() {

    private var applicationComponent: ApplicationComponent? = null

    fun getApplicationComponent(): ApplicationComponent {
        synchronized(this) {
            var component = applicationComponent

            if (component == null) {
                component = DaggerApplicationComponent
                        .builder()
                        .applicationModule(ApplicationModule(this))
                        .build()
                applicationComponent = component
            }

            return component!!
        }
    }
}