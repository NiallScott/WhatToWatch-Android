package ch.whattowat.android.dagger

import dagger.Module
import android.arch.lifecycle.ViewModelProvider
import ch.whattowat.android.ui.main.MainViewModelModule
import dagger.Binds

@Module(includes = [ MainViewModelModule::class ])
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}