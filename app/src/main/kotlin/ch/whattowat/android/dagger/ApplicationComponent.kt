package ch.whattowat.android.dagger

import android.app.Application
import ch.whattowat.android.WhatToWatchApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class
])
interface ApplicationComponent {

    fun inject(app: WhatToWatchApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}