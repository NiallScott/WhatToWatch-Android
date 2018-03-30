package ch.whattowat.android.dagger

import ch.whattowat.android.ui.main.MainActivityModule
import dagger.Module

@Module(includes = [ MainActivityModule::class ])
class ActivityModule