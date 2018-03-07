package ch.whattowat.android.dagger

import ch.whattowat.android.WhatToWatchApplication
import dagger.Module

@Module(includes = arrayOf(
        NetworkModule::class,
        WhatToWatchApiModule::class,
        ViewModelModule::class,
        ActivityModule::class
))
class ApplicationModule(private val application: WhatToWatchApplication) {

}