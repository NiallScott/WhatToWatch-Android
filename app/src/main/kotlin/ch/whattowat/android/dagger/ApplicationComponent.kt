package ch.whattowat.android.dagger

import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun plusDataComponent(): DataComponent
}